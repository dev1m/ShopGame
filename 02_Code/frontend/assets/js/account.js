$(document).ready(function () {
  var url = new URL(window.location.href); // Lấy URL hiện tại
  var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
  var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
  var currentPage = 0; // Trang hiện tại
  var accountsPerPage = 8; // Số lượng account trên mỗi trang

  var linkAccount = "http://localhost:8080/account";
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  function formatNumberAndReplaceComma(number) {
    // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
    return Number(number).toLocaleString("en-US").replace(/,/g, ".");
  }
  function displayAccounts(accounts) {
    $("#listAccount").empty(); // Xóa danh sách hiện tại

    accounts.forEach(function (value, index) {
      // Tạo HTML để hiển thị account tại đây
      var detail = value.detail;
      var lines = detail.split("\n");
      var html = `
                    <div class="fade-in col-span-8 sm:col-span-4 md:col-span-2 lg:col-span-2 xl:col-span-2 border border-gray-300 relative listSearch" 
                    style="padding: 1px">
                    <div>
                      <div class="relative">
                        <a href="./detail_account.html?id=${value.id}">
                          <div class="relative">
                            <img
                              class="h-56 md:h-40 w-full object-fill object-center lazyLoad" src="http://localhost:8080/account/file/${value.img}"
                            />
                            <span
                              class="absolute v-text-1 bg-red-700 text-white font-bold text-sm inline-block px-2 rounded-sm account_id"
                              style="right: 5px; top: 5px"
                            >
                              ${value.id}
                            </span>
                          </div>
                        </a>
                        <div class="py-2 bg-gray-200 px-2"></div>
                        <div class="my-2 py-2 px-2 relative">
                        <div class="grid grid-cols-12 gap-3 text-white font-medium text-sm">`;
      for (var i = 0; i < lines.length && i < 4; i++) {
        var line = lines[i];
        var parts = line.split(": "); // Tách thông tin thành key và value

        // Kiểm tra nếu có đủ 2 phần tử (key và value)
        if (parts.length === 2) {
          var key1 = parts[0].trim();
          var value1 = parts[1].trim();

          // Thêm thông tin vào chuỗi HTML
          html += `
                                  <div class="col-span-6 text-center">
                                      <p>
                                      ${key1}:
                                          <b class="text-white-800">${value1}</b> 
                                      </p>
                                  </div>
                              `;
        }
      }
      html += `
                                                      
                            <div class="col-span-6 text-center">
                            </div>
                        </div>
                        </div>
                        <div
                          class="mt-4 rounded-b-sm grid grid-cols-12 gap-5 p-2"
                        >
                          <div class="col-span-6">
                            <ul
                              class="v-text-1 rounded-sm w-full font-medium"
                              style="font-weight: 500"
                            >
                              <p
                                class="w-full border border-red-600 text-center rounded text-white block px-3 py-1 money-value"
                              >
                              ${formatNumberAndReplaceComma(value.money)} VNĐ
                              </p>
                            </ul>
                          </div>
                          <div class="col-span-6">
                            <div class="w-full">
                              <a
                                href="./detail_account.html?id=${value.id}"
                                class="cursor-pointer border rounded w-full text-center cursor-pointer border-red-500 hover:border-yellow-500 bg-red-500 transition duration-200 hover:bg-yellow-500 text-white uppercase inline-block font-semibold py-1 px-3"
                              >
                                Chi tiết
                              </a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                `;
      $("#listAccount").append(html);
    });
  }
  function getAccountsByPage(page) {
    $.ajax({
      method: "GET",
      url: `${linkAccount}/pt/${id}?page=${page}&size=${accountsPerPage}`,
      headers: {
        Authorization: header,
      },
    }).done(function (msg) {
      // console.log(msg);
      if (msg.content) {
        var accounts = msg.content; // Dữ liệu của trang hiện tại
        displayAccounts(accounts);

        // Cập nhật trạng thái phân trang tại đây (thêm nút điều hướng trang)
        var totalPages = msg.totalPages;

        // Xóa nút trang trước nếu cần
        $("#prev-page").remove();
        // Xóa nút trang kế tiếp nếu cần
        $("#next-page").remove();

        // Cập nhật nút trang trước
        if (currentPage > 0) {
          $("#pagination").append(
            `<button id="prev-page" class="previous round">&#8249;</button>`
          );
          $("#prev-page").click(function () {
            currentPage--;
            getAccountsByPage(currentPage);
          });
        }

        // Cập nhật nút trang kế tiếp
        if (currentPage < totalPages - 1) {
          $("#pagination").append(
            `<button id="next-page" class="next round">&#8250;</button>`
          );
          $("#next-page").click(function () {
            currentPage++;
            getAccountsByPage(currentPage);
          });
        }
      }
    });
  }

  // Gọi hàm ban đầu để hiển thị trang đầu tiên
  getAccountsByPage(currentPage);

  $.ajax({
    method: "GET",
    url: `http://localhost:8080/group/getGroupId/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    if (msg.success) {
      var title = `${msg.data.title}`;
      $("#titleAccount").append(title);
      var desc = `${msg.data.description}`;
      $("#descAccount").append(desc);
    }
  });
});
$(document).ready(function () {
  // Hàm kiểm tra xem giá có nằm trong khoảng không
  function isMoneyInRange(money, range) {
    switch (range) {
      case "1":
        return money >= 0 && money <= 100000;
      case "2":
        return money > 100000 && money <= 500000;
      case "3":
        return money > 500000 && money <= 1000000;
      case "4":
        return money > 1000000;
      default:
        return true; // Nếu không chọn khoảng giá thì hiển thị tất cả
    }
  }
  $("#searchAccount").click(function () {
    var idToSearch = $("#id_acc").val();
    var moneyRange = $("#money_acc").val();

    // Lặp qua danh sách các tài khoản và kiểm tra id_acc và money_acc
    $(".listSearch").each(function () {
      var accountId = $(this).find(".account_id").text().trim();
      var money = parseInt(
        $(this).find(".money-value").text().trim().replace(/\D/g, "")
      );

      // Kiểm tra id_acc và money_acc và hiển thị/ẩn phần tử tương ứng
      if (
        (idToSearch === "" || accountId === idToSearch) &&
        isMoneyInRange(money, moneyRange)
      ) {
        $(this).show();
      } else {
        $(this).hide();
      }
    });
  });
});
