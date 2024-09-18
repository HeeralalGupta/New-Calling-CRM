$(document).ready(function(){
  $("#r-search").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#ruralTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});