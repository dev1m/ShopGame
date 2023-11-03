$(document).on("click", "#ThemChuyenMuc", function () {
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var formData = new FormData($("#categoryGameForm")[0]);
  $.ajax({
    method: "POST",
    url: `http://localhost:8080/category/addCategoryGame`,
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
        text: "Thêm thành công!",
      }).then((result) => {
        setTimeout(() => {
          location.reload();
        }, 500);
      });
    }
  });
});

$(document).ready(function () {
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var linkCategory = `http://localhost:8080/category`;
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/category`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    $.each(msg.data, function (index, value) {
      var html = `<tr>
            <td>${index + 1}</td>
            <td width="10%"><img width="100%" src="${linkCategory}/file/${
        value.img
      }" /></td>
            <td>${value.title}</td>
            `
      if (value.display === "SHOW") {
        html += `<td><span class="badge badge-success">HIỂN THỊ</span></td>`;
      } else {
        html += `<td><span class="badge badge-danger">ẨN</span></td>`;
      }
      html += `
            <td>
                <button class="btn btn-primary" id="btnEdit" data-title="${value.title}"
                    data-display="${value.display}" data-id="${value.id}"><i
                        class="fas fa-edit"></i>
                    <span>EDIT</span></button>

                <a type="button"
                    href="./group_game.html?id=${value.id}"
                    class="btn btn-info"><i class="fas fa-file-medical"></i>
                    <span>XEM NHÓM</span></a>
                <button  id="deleteCategory" data-id="${value.id}" class="btn btn-warning">
                    <i class="fa-sharp fa-solid fa-trash"></i>
                    <span>DELETE</span>
                </button>
            </td>
        </tr>`;
      $("#listCategory").append(html);
    });
  });
});
$(document).on("click", "#btnEdit", function () {
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var title = $(this).data("title");
  var display = $(this).data("display");
  var id_category = $(this).data("id");

  $("#title_edit").val(title);
  $("#display_edit").val(display);
  $("#staticBackdrop").modal("show");

  $("#LuuChuyenMuc").click(function () {
    var title = $("#title_edit").val();
    var imgInput = document.getElementById('img_edit');
    var display = $("#display_edit").val();
    var formData = new FormData();
    formData.append("id", id_category);
    formData.append("title", title);
    formData.append("display", display);
    if (imgInput.files.length > 0) {
      formData.append("img", imgInput.files[0]);
    }
    $.ajax({
      method: "POST",
      url: `http://localhost:8080/category/update`,
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
$(document).on("click", "#deleteCategory", function () {
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var id = $(this).data("id");
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/category/delete/${id}`,
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
    } else {
      Swal.fire({
        icon: "error",
        title: "Thất bại",
        text: "Bên trong danh mục còn nhóm khác nên không thể xóa!",
      }).then((result) => {
        setTimeout(() => {
          location.reload();
        }, 500);
      });
    }
  });
});
