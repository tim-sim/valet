package org.tim.dao;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.tim.domain.Tag;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author tim
 */
@Repository
public class TagsDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(final Tag tag) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement("insert into TAGS (name) values (?)");
                statement.setString(1, tag.getName());
                return statement;
            }
        }, keyHolder);
        tag.setId(keyHolder.getKey().longValue());
    }

    public void delete(long id) {
        jdbcTemplate.update("delete from TAGS where id = ?", id);
    }

    public void deleteOrphan(Tag tag) {
        // todo: implement
    }


    public void merge(Tag tag) {
        Tag existent = findByName(tag.getName());
        if (existent == null) {
            create(tag);
        } else {
            tag.setId(existent.getId());
        }
    }

    public Tag findByName(String tagName) {
        List<Tag> tags = jdbcTemplate.query("select * from TAGS where NAME = ?", TAG_MAPPER, tagName);
        return tags.isEmpty() ? null : tags.get(0);
    }

    public List<Tag> findByNote(long noteId) {
        return jdbcTemplate.query("select t.* from TAGS t, NOTES_TAGS nt where nt.TAG_ID = t.ID and nt.NOTE_ID = ?", TAG_MAPPER, noteId);
    }

    public Map<Tag, Long> collectUsages() {
        return jdbcTemplate.query("select t.*, count(nt.NOTE_ID) from TAG t, NOTES_TAGS nt where nt.TAG_ID = t.ID group by nt.TAG_ID", HITS_MAPPER);
    }

    private static RowMapper<Tag> TAG_MAPPER = new RowMapper<Tag>() {
        @Override
        public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
            Tag tag = new Tag();
            tag.setId(resultSet.getLong("id"));
            tag.setName(resultSet.getString("name"));
            return tag;
        }
    };

    private static ResultSetExtractor<Map<Tag, Long>> HITS_MAPPER = new ResultSetExtractor<Map<Tag, Long>>() {
        @Override
        public Map<Tag, Long> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Tag, Long> usages = Maps.newHashMap();
            while (resultSet.next()) {
                Tag tag = TAG_MAPPER.mapRow(resultSet, 0);
                usages.put(tag, resultSet.getLong(2));
            }
            return usages;
        }
    };

}
