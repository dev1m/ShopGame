$(document).ready(function () {
  var token = localStorage.getItem("token");
  var usernameToken = localStorage.getItem("username");
  if (usernameToken == null) {
    window.location.href = "../index.html";
  }
  function formatNumberAndReplaceComma(number) {
    // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
    return Number(number).toLocaleString("en-US").replace(/,/g, ".");
  }
  function getUsernameById(id_user, callback) {
    var linkUser = `http://localhost:8080/user/getUserById/${id_user}`;

    $.ajax({
      method: "GET",
      url: linkUser,
      headers: {
        Authorization: header,
      },
    }).done(function (response) {
      var username_by = response.data.username;
      callback(username_by);
    });
  }
  var header = "Bearer " + token;
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/user/get/${usernameToken}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    if (msg.data.level !== "admin") {
      window.location.href = "../index.html";
    } else {
      $.ajax({
        method: "GET",
        url: `http://localhost:8080/user/countUsers`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        var html = `<div class="inner">
                <h3>${msg.data}</h3>
                <p>Tổng thành viên</p>
            </div>
            <div class="icon">
                <i class="fas fa-users"></i>
            </div>`;
        $("#countUser").append(html);
      });
      $.ajax({
        method: "GET",
        url: `http://localhost:8080/user/sumUserMoney`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        var html = `<div class="inner">
                        <h3>${formatNumberAndReplaceComma(msg.data)} VNĐ
                        </h3>
                        <p>Tổng số dư thành viên</p>
                    </div>
                    <div class="icon">
                        <i class="fas fa-money-bill-alt"></i>
                    </div>`;
        $("#sumUserMoney").append(html);
      });
      $.ajax({
        method: "GET",
        url: `http://localhost:8080/account/countAccountsByUserIdIsNull`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        var html = `<div class="inner">
                <h3>${msg.data}</h3>
                <p>Tài khoản đang bán</p>
            </div>
            <div class="icon">
                <i class="fas fa-shopping-cart"></i>
            </div>`;
        $("#countAccountsByUserIdIsNull").append(html);
      });
      $.ajax({
        method: "GET",
        url: `http://localhost:8080/account/countAccountsByUserIdIsNotNull`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        var html = `<div class="inner">
                <h3>${msg.data}</h3>
                <p>Tài khoản đã bán</p>
            </div>
            <div class="icon">
                <i class="fas fa-store"></i>
            </div>`;
        $("#countAccountsByUserIdIsNotNull").append(html);
      });
      $.ajax({
        method: "GET",
        url: `http://localhost:8080/account/sumMoneySell`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        if (msg.data == null) {
          var html = `<div class="inner">
                <h3>0 VNĐ
                </h3>
                <p>Tổng tiền đã bán</p>
            </div>
            <div class="icon">
                <i class="fas fa-money-bill-alt"></i>
            </div>`;
          $("#sumMoneySell").append(html);
        } else {
          var html = `<div class="inner">
                <h3>${formatNumberAndReplaceComma(msg.data)} VNĐ
                </h3>
                <p>Tổng tiền đã bán</p>
            </div>
            <div class="icon">
                <i class="fas fa-money-bill-alt"></i>
            </div>`;
          $("#sumMoneySell").append(html);
        }
      });
      $.ajax({
        method: "GET",
        url: `http://localhost:8080/cashFlow/getAll`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        $.each(msg.data, function (index, value) {
          var html = `<tr>
                    <td>${index + 1}</td>
                    <td id="usernameBy_${value.id}">${getUsernameById(
            value.user_id,
            function (username_by) {
              var username_by1 = document.getElementById(
                `usernameBy_${value.id}`
              );
              username_by1.textContent = username_by;
            }
          )}</td>
                    <td>${formatNumberAndReplaceComma(value.cash_old)}</td>
                    <td>${formatNumberAndReplaceComma(value.cash_change)}</td>
                    <td>${formatNumberAndReplaceComma(value.cash_new)}</td>
                    `;
          var inputTime = value.time;
          var dateObj = new Date(inputTime);
          var formattedTime = dateObj.toLocaleString();
          html += `<td><span class="badge badge-dark">${formattedTime}</span></td>`;
          html += `
                    <td>${value.cash_note}</td>
                </tr>`;
                
          $("#listBienDongSoDu").append(html);
          
          
        });
        $("#datatable").DataTable({
          pageLength: 100,
          language: {
            sProcessing: "Đang xử lý...",
            sLengthMenu: "Xem _MENU_ mục",
            sZeroRecords: "Không tìm thấy dòng nào phù hợp",
            sInfo: "Đang xem _START_ đến _END_ trong tổng số _TOTAL_ mục",
            sInfoEmpty: "Đang xem 0 đến 0 trong tổng số 0 mục",
            sInfoFiltered: "(được lọc từ _MAX_ mục)",
            sInfoPostFix: "",
            sSearch: "Tìm:",
            sUrl: "",
            oPaginate: {
              sFirst: "Đầu",
              sPrevious: "Trước",
              sNext: "Tiếp",
              sLast: "Cuối",
            },
          },
        });
      });
      $.ajax({
        method: "GET",
        url: `http://localhost:8080/order/countOrderCayThue`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        var html = `${msg.data}`;
        $("#countOrderCayThue").append(html);
      });
    }
  });
});
