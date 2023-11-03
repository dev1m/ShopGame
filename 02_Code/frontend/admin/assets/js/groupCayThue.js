$(document).ready(function () {
  var url = new URL(window.location.href); // Lấy URL hiện tại
  var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
  var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  function formatNumberAndReplaceComma(number) {
    // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
    return Number(number).toLocaleString("en-US").replace(/,/g, ".");
  }
  var linkCategoryCayThue = `http://localhost:8080/category_caythue`;
  $.ajax({
    method: "GET",
    url: `${linkCategoryCayThue}/get/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    var html = `
                <div class="col-sm-6">
                    <h1>NHÓM ${msg.data.title} </h1>
                </div>
              `;
    $("#titleCategoryCayThue").append(html);
    var img = `<img width="100%" src="${linkCategoryCayThue}/file/${msg.data.img}" />`;
    $("#imgCategoryCayThue").append(img);
  });
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/group_caythue/get/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    $.each(msg.data, function (index, value) {
      var html = ` 
        <tr>
            <td>${index + 1}</td>
            <td>${value.title}</td>
            <td>${formatNumberAndReplaceComma(value.money)}</td>
            `;
      if (value.display === "SHOW") {
        html += `<td><span class="badge badge-success">HIỂN THỊ</span></td>`;
      } else {
        html += `<td><span class="badge badge-danger">ẨN</span></td>`;
      }
      html += `
            <td>
                <button class="btn btn-primary" id="btnEdit" data-title="${value.title}" data-money="${value.money}" data-display="${value.display}" data-id="${value.id}"><i
                        class="fas fa-edit"></i>
                    <span>EDIT</span>
                </button>
                <button  id="deleteGroupCayThue" data-id="${value.id}" class="btn btn-warning">
                    <i class="fa-sharp fa-solid fa-trash"></i>
                    <span>DELETE</span>
                </button>
            </td>
        </tr>`;
      $("#listGroupCayThue").append(html);
    });
  });
});
$(document).on("click", "#ThemChuyenMuc", function (event) {
  event.preventDefault();
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var url = new URL(window.location.href); // Lấy URL hiện tại
  var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
  var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
  var category_caythue_id = Number(id);
  var formData = new FormData(); // Sử dụng FormData để tạo dữ liệu form multipart
  formData.append("category_caythue_id", category_caythue_id);
  formData.append("title", $("#title").val());
  formData.append("display", $("#display").val());
  formData.append("money", $("#money").val());
  $.ajax({
    method: "POST",
    url: `http://localhost:8080/group_caythue/addGroupCayThue`,
    data: formData,
    processData: false,
    contentType: false,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    if (msg.data) {
      Swal.fire({
        icon: "success",
        title: "Thành công",
        text: "Thêm  thành công!",
      }).then((result) => {
        setTimeout(() => {
          location.reload();
        }, 500);
      });
    }
  });
});
$(document).on("click", "#deleteGroupCayThue", function () {
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var id = $(this).data("id");
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/group_caythue/delete/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    if (msg.data) {
      Swal.fire({
        icon: "success",
        title: "Thành công",
        text: "xóa thành công!",
      }).then((result) => {
        setTimeout(() => {
          location.reload();
        }, 500);
      });
    }
  });
});
$(document).on("click", "#btnEdit", function () {
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
    var title = $(this).data("title");
    var display = $(this).data("display");
    var money = $(this).data("money");
    var id_group_caythue = $(this).data("id");
  
    $("#title_edit").val(title);
    $("#display_edit").val(display);
    $("#money_edit").val(money);
    $("#staticBackdrop").modal("show");
  
    $("#LuuChuyenMuc").click(function () {
      var title = $("#title_edit").val();
      var display = $("#display_edit").val();
      var money = $("#money_edit").val();
      var formData = new FormData();
      formData.append("id", id_group_caythue);
      formData.append("title", title);
      formData.append("display", display);
      formData.append("money", money);
      
      $.ajax({
        method: "POST",
        url: `http://localhost:8080/group_caythue/update`,
        data: formData,
        processData: false,
        contentType: false,
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
              location.reload();
            }, 500);
          });
        }
      });
    });
  });