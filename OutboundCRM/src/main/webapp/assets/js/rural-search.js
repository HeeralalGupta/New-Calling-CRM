$(document).ready(function(){
  $("#r-search").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#ruralTable tbody tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});