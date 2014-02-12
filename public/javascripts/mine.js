/**
 * Created by serega on 12.02.14.
 */
function deleteSome(link) {
    $.ajax({
        url: link,
        type: 'DELETE',
        success: function(result) {
            // Do something with the result
        }
    });
}

function putSome(link) {
    $.ajax({
        url: link,
        type: 'PUT',
        data: $('form').serialize(),
    success: function(result) {
            // Do something with the result
        }
    });
}