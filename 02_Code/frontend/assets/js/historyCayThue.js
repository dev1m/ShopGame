$(document).ready(function () {
  var token = localStorage.getItem("token");
  var username = localStorage.getItem("username");
  var header = "Bearer " + token;

  function formatNumberAndReplaceComma(number) {
    // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
    return Number(number).toLocaleString("en-US").replace(/,/g, ".");
  }
  var dataTable; // Khai báo biến DataTable mà bạn sẽ sử dụng sau này.
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
      url: `http://localhost:8080/order/getByUserId/${id_user}`,
      headers: {
        Authorization: header,
      },
    }).done(function (msg1) {
      if (msg1.success) {
        var dataToAdd = [];
        $.each(msg1.data, function (index, value) {
          var id_group_cay_thue = value.group_caythue_id;
          $.ajax({
            method: "GET",
            url: `http://localhost:8080/group_caythue/getGroupCayThue/${id_group_cay_thue}`,
            headers: {
              Authorization: header,
            },
          }).done(function (msg2) {
            var titleGroupCayThue = msg2.data.title;
            if (value.status === "xuly") {
              var status = `Đang xử lý`;
            } else if (value.status === "huy") {
              status = `Hủy`;
            } else {
              status = `Thành công`;
            }
            var rowData = [
              index + 1,
              titleGroupCayThue,
              value.username,
              value.password,
              formatNumberAndReplaceComma(value.money) + " VNĐ",
              status,
              value.note_admin,
            ];
            dataToAdd.push(rowData); // Thêm dữ liệu mới vào mảng dataToAdd.

            if (dataToAdd.length === msg1.data.length) {
              // Nếu đã thêm đủ dữ liệu, hãy thêm chúng vào DataTable.
              if (dataTable) {
                // Nếu DataTable đã tồn tại, sử dụng rows.add() để thêm dữ liệu mới.
                dataTable.rows.add(dataToAdd).draw();
              } else {
                // Nếu DataTable chưa tồn tại, hãy khởi tạo nó và thêm dữ liệu.
                dataTable = $("#datatableCayThue").DataTable({
                  pageLength: 100,
                  language: {
                    sProcessing: "Đang xử lý...",
                    sLengthMenu: "Xem _MENU_ mục",
                    sZeroRecords: "Không tìm thấy dòng nào phù hợp",
                    sInfo:
                      "Đang xem _START_ đến _END_ trong tổng số _TOTAL_ mục",
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
                  responsive: true,
                  autoWidth: false,
                });
                dataTable.rows.add(dataToAdd).draw();
              }
            }
          });
        });
      }
    });
  });
});
