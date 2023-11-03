$(document).ready(function () {
  var url = new URL(window.location.href); // Lấy URL hiện tại
  var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
  var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
  var token = localStorage.getItem("token");
  var usernameToken = localStorage.getItem("username");
  var header = "Bearer " + token;
  function formatNumberAndReplaceComma(number) {
    // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
    return Number(number).toLocaleString("en-US").replace(/,/g, ".");
  }
  var linkCategory = `http://localhost:8080/category`;
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/category/get/title/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    var html = `
                <div class="col-sm-6">
                    <h1>NHÓM ${msg.data}</h1>
                </div>
            `;
    $("#titleCategory").append(html);
  });
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/category/getById/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    var html = `
            
            <img width="100%" src="${linkCategory}/file/${msg.data.img}" />
            
            `;
    $("#imgCategory").append(html);
  });


  $.ajax({
    method: "GET",
    url: `http://localhost:8080/group/get/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    $.each(msg.data, function (index, value) {
      var html = `
    
    <tr>
          <td>${index + 1}</td>
          <td width="10%"><img width="100%" src="${linkCategory}/file/${value.img}" /></td>
          <td>${value.title}</td>
          <td>${value.description}</td>
          `
          if (value.display === "SHOW") {
            html += `<td><span class="badge badge-success">HIỂN THỊ</span></td>`;
          } else {
            html += `<td><span class="badge badge-danger">ẨN</span></td>`;
          }
          html += `
          <td>
              <a class="btn btn-primary"
                  href="./edit_group.html?id=${value.id}"><i
                      class="fas fa-edit"></i>
                  <span>EDIT</span></a>

              <a type="button" href="./account.html?id=${value.id}" class="btn btn-info"><i
                      class="fas fa-file-medical"></i>
                  <span>DANH SÁCH ACCOUNT</span>
              </a>
              <button  id="deleteGroup" data-id="${value.id}" class="btn btn-warning">
                  <i class="fa-sharp fa-solid fa-trash"></i>
                  <span>DELETE</span>
              </button>
          </td>
    </tr>
            `;
    $("#listGroup").append(html);
    })
    
  });




});
$(document).on("click", "#ThemChuyenMuc", function (event) {
  event.preventDefault();
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var url = new URL(window.location.href); // Lấy URL hiện tại
  var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
  var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
  var id_category = Number(id);
  var formData = new FormData(); // Sử dụng FormData để tạo dữ liệu form multipart
  formData.append("category_id", id_category);
  formData.append("title", $("#title").val());
  formData.append("display", $("#display").val());
  formData.append("img", $("#img")[0].files[0]);
  formData.append("description", $("#description").val());
  $.ajax({
    method: "POST",
    url: `http://localhost:8080/group/addGroupGame`,
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
$(document).on("click", "#deleteGroup", function () {
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var id = $(this).data("id");
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/group/delete/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    if(msg.data){
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
    else{
      Swal.fire({
        icon: "error",
        title: "Thất bại",
        text: "Bên trong nhóm còn có các account khác nên không thể xóa!",
      }).then((result) => {
        setTimeout(() => {
          location.reload();
        }, 500);
      });
    }
  });
});