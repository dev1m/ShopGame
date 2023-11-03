$(document).ready(function () {
  var token = localStorage.getItem("token");
  var usernameToken = localStorage.getItem("username");
  var header = "Bearer " + token;
  function formatNumberAndReplaceComma(number) {
    // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
    return Number(number).toLocaleString("en-US").replace(/,/g, ".");
  }
  function getTitleByIdOrCayThue(idGroupCayThue, callback) {
    $.ajax({
      method: "GET",
      url: `http://localhost:8080/group_caythue/getGroupCayThue/${idGroupCayThue}`,
      headers: {
        Authorization: header,
      },
    }).done(function (response) {
      var title = response.data.title;
      callback(title);
    });
  }
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/order/getAll`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    $.each(msg.data, function (index, value) {
      var html = ` <tr>
                            <td>${index + 1}</td>
                            <td id="title_${value.id}" width="20%">${getTitleByIdOrCayThue(
        value.group_caythue_id,
        function (title) {
          var titleBy = document.getElementById(`title_${value.id}`);
          titleBy.textContent = title;
        }
      )}</td>
                              <td>${value.username}</td>
                            <td>${value.password}</td>
                            <td><textarea class="form-control" readonly>${
                              value.note_user
                            }</textarea></td>
                            <td>${formatNumberAndReplaceComma(
                              value.money
                            )} VNĐ</td>
                            <td>
                            `;
      if (value.status === "xuly") {
        html += `<span class="badge badge-info">Đang xử lý</span>`;
      } else if (value.status === "hoantat") {
        html += `<span class="badge badge-success">Hoàn tất</span>`;
      } else {
        html += `<span class="badge badge-danger">Hủy</span>`;
      }

      html += `
                            </td>                      
                            <td>
                                <button class="btn btn-primary" id="btnEdit" data-status="${value.status}" data-id="${value.id}"><i class="fas fa-edit"></i>
                                    <span>EDIT</span></button>
                            </td>
                        </tr>`;
      $("#listOrderCayThue").append(html);
    });
  });
});
$(document).on("click", "#btnEdit", function () {
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
  var status = $(this).data("status"); // Lấy giá trị data-status từ nút
  // Lấy giá trị data-id từ nút
  var id_order = $(this).data("id");
  // Đặt giá trị của modal dựa trên dữ liệu từ nút
  $("#id_order").val(id_order);
  $("#status").val(status);

  // Hiển thị modal
  $("#editModal").modal("show");

  $("#SaveOrderCayThue").click(function () {
    var id_order_cay_thue = $("#id_order").val();
    var status = $("#status").val();
    var note_admin = $("#note_admin").val();
    $.ajax({
        method: "GET",
        url: `http://localhost:8080/order/getById/${id_order_cay_thue}`,
        headers: {
          Authorization: header,
        },
    }).done(function (msg) {
        var status_order = msg.data.status;
        if(status_order === "huy"){
            Swal.fire({
                icon: "error",
                title: "Lỗi",
                text: "Đơn hàng này đã hủy không thể hoàn tất",
            });
        }
        else{
            $.ajax({
                method: "POST",
                url: `http://localhost:8080/order/update`,
                data: {
                  id: id_order_cay_thue,
                  status: status,
                  note_admin: note_admin,
                },
                headers: {
                  Authorization: header,
                },
              }).done(function (msg) {
                if (msg.data) {
                  Swal.fire({
                    icon: "success",
                    title: "Thành công",
                    text: "Cập nhật thành công!",
                  }).then((result) => {
                    setTimeout(() => {
                      window.location.href = "./don_hang_cay_thue.html";
                    }, 500);
                  });
                }
              });
        }
    });
    if (status === "huy") {
      $.ajax({
        method: "GET",
        url: `http://localhost:8080/order/getById/${id_order_cay_thue}`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        var user_id = msg.data.user_id;
        var money_order = msg.data.money;
        $.ajax({
          method: "GET",
          url: `http://localhost:8080/user/getUserById/${user_id}`,
          headers: {
            Authorization: header,
          },
        }).done(function (msg) {
          var money_user = msg.data.money;
          var money_new = money_order + money_user;
          var user_name = msg.data.username;
          $.ajax({
            method: "POST",
            url: `http://localhost:8080/user/updateCashFlow`,
            data: {
              username: user_name,
              newMoney: money_new,
            },
            headers: {
              Authorization: header,
            },
          }).done(function (msg) {
            $.ajax({
              method: "POST",
              url: `http://localhost:8080/cashFlow/addCashFlow`,
              data: {
                cashOld: money_user,
                cashChange: money_order,
                cashNew: money_new,
                cashNote: `Hoàn trả dịch vụ cày thuê (# ${id_order_cay_thue})`,
                userId: user_id,
              },headers: {
                Authorization: header,
              },
            });
          });
        });
      });
    }
  });
});
