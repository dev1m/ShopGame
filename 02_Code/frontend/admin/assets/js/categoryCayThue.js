$(document).on("click", "#ThemChuyenMuc", function () {
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
    var title = $("#title").val();
    var imgInput = document.getElementById('img');
    var display = $("#display").val();
    var formData = new FormData();
    formData.append("title", title);
    formData.append("img", imgInput.files[0]);
    formData.append("display", display);
    $.ajax({
      method: "POST",
      url: `http://localhost:8080/category_caythue/addCategory_caythue`,
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
    var linkCategoryCayThue = `http://localhost:8080/category_caythue`;
    $.ajax({
      method: "GET",
      url: linkCategoryCayThue,
      headers: {
        Authorization: header,
      },
    }).done(function (msg) {
      $.each(msg.data, function (index, value) {
        var html = `
        <tr>
            <td>${index + 1}</td>
            <td width="10%"><img width="100%" src="${linkCategoryCayThue}/file/${value.img}" /></td>
            <td>${value.title}</td>
            `
            if (value.display === "SHOW") {
                html += `<td><span class="badge badge-success">HIỂN THỊ</span></td>`;
            } else {
                html += `<td><span class="badge badge-danger">ẨN</span></td>`;
            }
            html += `
            <td>
                <button class="btn btn-primary" id="btnEdit" data-title="${value.title}" data-display="${value.display}" data-id="${value.id}">
                <i class="fas fa-edit"></i>
                <span>EDIT</span>
            </button>

                <a type="button" href="./group_caythue.html?id=${value.id}" class="btn btn-info">
                  <i class="fas fa-file-medical"></i>
                    <span>XEM NHÓM</span>
                </a>
                <button  id="deleteCategoryCayThue" data-id="${value.id}" class="btn btn-warning">
                    <i class="fa-sharp fa-solid fa-trash"></i>
                    <span>DELETE</span>
                </button>
            </td>
        </tr>`;
        $("#listCategoryCayThue").append(html);
      });
    });
  });
  $(document).on("click", "#btnEdit", function () {
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
    var title = $(this).data("title");
    var display = $(this).data("display");
    var id_categoryCayThue = $(this).data("id");
  
    $("#title_edit").val(title);
    $("#display_edit").val(display);
    $("#staticBackdrop").modal("show");
  
    $("#LuuChuyenMuc").click(function () {
      var title = $("#title_edit").val();
      var imgInput = document.getElementById('img_edit');
      var display = $("#display_edit").val();
      var formData = new FormData();
      formData.append("id", id_categoryCayThue);
      formData.append("title", title);
      formData.append("display", display);
      if (imgInput.files.length > 0) {
        formData.append("img", imgInput.files[0]);
      }
      $.ajax({
        method: "POST",
        url: `http://localhost:8080/category_caythue/update`,
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
  $(document).on("click", "#deleteCategoryCayThue", function () {
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
    var id = $(this).data("id");
    $.ajax({
      method: "GET",
      url: `http://localhost:8080/category_caythue/delete/${id}`,
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
  