(function($, valet) {
    var KBD_ENTER = 13;

    var addNoteForm = $("#addNoteForm");
    addNoteForm.keydown(function (event) {
        if (event.keyCode == KBD_ENTER) {
            addNoteForm.submit();
        }
    });
}) (jQuery, window.valet = window.valet || {})