/*$(document).ready(function(){
  $("u-search").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#urbanTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});*/

var $rows = $('#urbanTable tr');
$('#u-search').keyup(function() {
    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
    
    $rows.show().filter(function() {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
});