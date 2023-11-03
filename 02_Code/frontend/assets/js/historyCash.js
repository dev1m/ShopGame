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
      url: `http://localhost:8080/cashFlow/getByUserId/${id_user}`,
      headers: {
        Authorization: header,
      },
    }).done(function (msg1) {
      if (msg1.success) {
        var dataToAdd = [];
        $.each(msg1.data, function (index, value) {
          var inputTime = value.time;
          var dateObj = new Date(inputTime);
          var formattedTime = dateObj.toLocaleString();
          var rowData = [
            index + 1,
            formatNumberAndReplaceComma(value.cash_old) + " VNĐ",
            formatNumberAndReplaceComma(value.cash_change) + " VNĐ",
            formatNumberAndReplaceComma(value.cash_new) + " VNĐ",
            formattedTime,
            value.cash_note,
          ];
          dataToAdd.push(rowData); // Thêm dữ liệu mới vào mảng dataToAdd.

          if (dataToAdd.length === msg1.data.length) {
            // Nếu đã thêm đủ dữ liệu, hãy thêm chúng vào DataTable.
            if (dataTable) {
              // Nếu DataTable đã tồn tại, sử dụng rows.add() để thêm dữ liệu mới.
              dataTable.rows.add(dataToAdd).draw();
            } else {
              // Nếu DataTable chưa tồn tại, hãy khởi tạo nó và thêm dữ liệu.
              dataTable = $("#datatableCash").DataTable({
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
                responsive: true,
                autoWidth: false,
              });
              dataTable.rows.add(dataToAdd).draw();
            }
          }
        });
      }
    });
  });
});
