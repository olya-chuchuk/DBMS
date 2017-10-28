$(function () {

    var $columns = $('#columns');
    var $columnName = $('#columnName');
    var $columnType = $('#columnType');

    $.ajax({
        type: 'PUT',
        url: '../rest/config',
        success: function () {
            console.log('initialized new tableConfig');
        }
    })
    $('#addButton').on('click', function () {
        $.ajax({
            type: 'POST',
            url: '../rest/config?columnName=' + $columnName.val() +
            '&columnType=' + $columnType.val(),
            success: function () {
                console.log('wrote to the db');
            }
        })
        $columns.append(
            '<tr><td>' + $columnName.val() + '</td><td>' + $columnType.val() + '</td></tr>'
        )
        $columnName.val('');
        $columnType.val('IntegerType');
    })
});