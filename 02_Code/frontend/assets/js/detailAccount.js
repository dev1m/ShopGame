$(document).ready(function () {
  var url = new URL(window.location.href); // Lấy URL hiện tại
  var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
  var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
  var token = localStorage.getItem("token");
  var username = localStorage.getItem("username");
  var header = "Bearer " + token;
  function formatNumberAndReplaceComma(number) {
    // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
    return Number(number).toLocaleString("en-US").replace(/,/g, ".");
  }
  function getTitleGroupById(groupId, callback) {
    var link = `http://localhost:8080/group/getGroupId/${groupId}`;

    $.ajax({
      method: "GET",
      url: link,
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
    url: `http://localhost:8080/account/getAccountId/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    if (msg.data !== null) {
      var list_img = msg.data.list_img;

      // Kiểm tra nếu list_img là một chuỗi, bạn có thể chuyển nó thành một mảng bằng cách tách các phần tử bằng dấu ','
      if (typeof list_img === "string") {
        list_img = list_img.split(",");
      }
      var html = `
        <div class="mb-4 py-4 md:p-4 bg-box-dark">
        <div class="fade-in mb-2 py-2 md:mb-4 block uppercase md:py-4 text-center text-yellow-400 md:text-3xl text-2xl font-extrabold text-fill " id="idGroup">
            DANH MỤC:${getTitleGroupById(msg.data.group_id, function (title) {
              var titleGr = document.getElementById(`idGroup`);
              titleGr.textContent = title;
            })}
        </div>
        <div class="sticky col-span-12 grid grid-cols-10 gap-2 select-none py-2 px-2 color-grant text-xl font-bold rounded"
            style="background: rgb(37 37 37); top: 60px; z-index: 98;">
            <div class="col-span-10 md:col-span-5">
                <div class="flex items-center flex-wrap text-yellow-500 pt-3">
                    <div class="relative">
                        ${formatNumberAndReplaceComma(msg.data.money)} VNĐ
                        <span class="absolute" style="top: -18px; left: 1px; font-size: 0.8rem;">
                            GIÁ BÁN
                        </span>
                    </div>
                </div>
            </div>
            <div
                class="v-skull-top col-span-10 md:col-span-5 text-yellow-500 flex justify-end items-center flex-wrap">
                <button type="button"
                    class="ml-4 flex bg-red-500 text-white items-center justify-center h-10 text-2xl rounded focus:outline-none px-4 text-center"
                    id="btnThanhToan">
                    THANH TOÁN
                </button>
            </div>
        </div>
        <div class="mt-4">
            <div class="v-account-detail p-2 md:px-0 mt-5">
                <div class="v-infomations border-t border-b border-gray-700 py-4 mb-10">
                    <div class="w-full grid grid-cols-12 gap-4">`;
      var detail = msg.data.detail; // Lấy thông tin từ chuỗi
      // Tạo một mảng các dòng thông tin
      var lines = detail.split("\n");
      for (var i = 0; i < lines.length && i < 4; i++) {
        var line = lines[i];
        var parts = line.split(": "); // Tách thông tin thành key và value

        // Kiểm tra nếu có đủ 2 phần tử (key và value)
        if (parts.length === 2) {
          var key1 = parts[0].trim();
          var value1 = parts[1].trim();

          // Thêm thông tin vào chuỗi HTML
          html += `   <div class="uppercase col-span-6 md:col-span-3 text-base md:text-xl font-semibold text-center">
                            <span class="text-white">${key1}: </span> <b class="text-yellow-600">
                            ${value1}
                            </b>
                        </div>`;
        }
      }
      html += `
                    
                    </div>
                </div>
            </div>
            <div class="v-account-detail p-2 md:px-0 mt-4">
                <div class="v-account-detail-1" id="taikhoan">
                    <div class="mb-10">
                    `;
      for (var i = 0; i < list_img.length; i++) {
        var imageURL = list_img[i];
        html += `<img src="http://localhost:8080/account/file/${imageURL}" data-sizes="auto"  class="border border-gray-400 mb-2 w-full lazyLoad lazy" />`;

        // Thực hiện xử lý với imageURL ở đây
      }
      html += `
                    
                        
                    
                    </div>
                </div>
            </div>
        </div>
    </div>
        `;
      $("#detailAccount").append(html);
    }
  });
});
$(document).on("click", "#btnThanhToan", function (e) {
  var url = new URL(window.location.href); // Lấy URL hiện tại
  var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
  var id_acc = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
  var token = localStorage.getItem("token");
  var username = localStorage.getItem("username");
  var header = "Bearer " + token;
  $("#btnThanhToan").html("ĐANG XỬ LÝ").prop("disabled", true);
  if (username == null) {
    Swal.fire({
      icon: "warning",
      title: "Thông báo",
      text: "Vui lòng đăng nhập để tiếp tục.",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Đăng nhập ngay",
    }).then((result) => {
      if (result.isConfirmed) {
        window.location.href = "./login.html";
      } else {
        $("#btnThanhToan").html("THANH TOÁN").prop("disabled", false);
      }
    });
  } else {
    Swal.fire({
      title: "Xác Nhận Thanh Toán",
      text: "Bạn có đồng ý thanh toán không ?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Thanh toán ngay",
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          method: "GET",
          url: `http://localhost:8080/user/get/${username}`,
          headers: {
            Authorization: header,
          },
        }).done(function (msg) {
          var id_user = msg.data.id;
          var money_user = msg.data.money;
          $.ajax({
            method: "GET",
            url: `http://localhost:8080/account/getAccountId/${id_acc}`,
            headers: {
              Authorization: header,
            },
          }).done(function (msg1) {
            var money_acc = msg1.data.money;
            if (money_acc > money_user) {
              Swal.fire({
                icon: "warning",
                title: "Thông báo",
                text: "Số dư không đủ vui lòng nạp thêm.",
              });
            } else {
              if (msg1.data.user_id !== -1) {
                Swal.fire({
                  icon: "warning",
                  title: "Thông báo",
                  text: "Tài khoản này đã bán, vui lòng tìm tài khoản khác.",
                });
              } else {
                $.ajax({
                  method: "POST",
                  url: `http://localhost:8080/account/order`,
                  data: {
                    id: id_acc,
                    user_id: id_user,
                  },
                  headers: {
                    Authorization: header,
                  },
                }).done(function (msg2) {
                  if (msg2.data) {
                    $.ajax({
                      method: "POST",
                      url: `http://localhost:8080/user/updateCashFlow`,
                      data: {
                        username: username,
                        newMoney: money_user - money_acc,
                      },
                      headers: {
                        Authorization: header,
                      },
                    });
                    $.ajax({
                      method: "POST",
                      url: `http://localhost:8080/cashFlow/addCashFlow`,
                      data: {
                        cashOld: money_user,
                        cashChange: money_acc,
                        cashNew: money_user - money_acc,
                        cashNote: `Mua tài khoản (# ${id_acc})`,
                        userId: id_user,
                      },
                      headers: {
                        Authorization: header,
                      },
                    });
                    Swal.fire({
                      icon: "success",
                      title: "Thành công",
                      text: "Thanh toán thành công!",
                    }).then((result) => {
                      setTimeout(() => {
                        window.location.href = "./history_account.html";
                      }, 1000);
                    });
                  } else {
                    Swal.fire({
                      icon: "error",
                      title: "Thất bại",
                      text: "Thanh toán thất bại!",
                    });
                  }
                });
              }
            }
          });
        });
      } else {
        $("#btnThanhToan").html("THANH TOÁN").prop("disabled", false);
      }
    });
  }
});
