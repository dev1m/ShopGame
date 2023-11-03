$(document).ready(function () {
  var token = localStorage.getItem("token");
  var username = localStorage.getItem("username");
  var header = "Bearer " + token;

  function formatNumberAndReplaceComma(number) {
    // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
    return Number(number).toLocaleString("en-US").replace(/,/g, ".");
  }
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/user/get/${username}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    var id_user = msg.data.id;
    $.ajax({
      method: "GET",
      url: `http://localhost:8080/card/getCardByUserId/${id_user}`,
      headers: {
        Authorization: header,
      },
    }).done(function (msg2) {
      if (msg2.success) {
        $.each(msg2.data, function (index, value) {
          var html = `     
            <tr>
                <td>${index + 1}</td>
                <td>${username}</td>
                <td>${value.card_seri}</td>
                <td>${value.card_pin}</td>
                <td>${value.card_type}</td>
                <td>${formatNumberAndReplaceComma(value.card_price)}</td>
                <td>${formatNumberAndReplaceComma(value.card_real_price)}</td>
                <td>${value.create_date}</td>
                <td>${value.card_status}</td>
                <td>${value.card_note}</td>
            </tr>
            
            `;
          $("#cardHistory").append(html);
        });
      }
    });
  });
});
