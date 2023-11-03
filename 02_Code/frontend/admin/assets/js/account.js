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
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/group/getGroupId/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    var title_group = `<h1>DANH SÁCH TÀI KHOẢN ${msg.data.title}</h1>`;
    $("#titleGroup").append(title_group);
    var img_group = `<img width="100%" src="http://localhost:8080/group/file/${msg.data.img}" />`;
    $("#imgGroup").append(img_group);
    var buttonAccount = `
        <button type="button" id="ThemTaiKhoan" name="ThemTaiKhoan" class="btn btn-primary btn-block">
            <span>THÊM NGAY</span></button>
        <a type="button" href="./group_game.html?id=${msg.data.category_id}" class="btn btn-danger btn-block waves-effect">
            <span>TRỞ LẠI</span>
        </a>
        `;
    $("#buttonAccount").append(buttonAccount);
  });
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/account/get/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    $.each(msg.data, function (index, value) {
      var html = `
                        <tr>
                                <td width="5%">${index + 1}</td>
                                <td width="5%">${value.id}</td>
                                <td width="10%"><img width="100%" src="http://localhost:8080/account/file/${
                                  value.img
                                }" /></td>
                                <td>${value.username_acc}</td>
                                `;
      var inputTime = value.date_submit;
      var dateObj = new Date(inputTime);
      var formattedTime = dateObj.toLocaleString();
      html += `
                                <td>
                                  ${formattedTime}
                                </td>
                                `;
      if (value.user_id === -1) {
        html += `<td><span class="badge badge-warning">ĐANG BÁN</span></td>`;
      } else {
        html += `<td><span class="badge badge-success">ĐÃ BÁN</span></td>`;
      }
      html += `
                                
                                <td>
                                    <a class="btn btn-primary" href="./edit_account.html?id=${value.id}">
                                        <i class="fas fa-edit"></i>
                                        <span>EDIT</span>
                                    </a>
                                    <button  id="deleteAccount" data-id="${value.id}" class="btn btn-warning">
                                        <i class="fa-sharp fa-solid fa-trash"></i>
                                        <span>DELETE</span>
                                    </button>
                                </td>
                        </tr>
                        `;
      $("#listAccount").append(html);
    });
  });
});
$(document).on("click", "#ThemTaiKhoan", function (event) {
  event.preventDefault();
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var url = new URL(window.location.href); // Lấy URL hiện tại
  var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
  var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
  var id_group = Number(id);
  $("#group_id").val(id_group);

  var group_id = $("#group_id").val();
  var username_acc = $("#username_acc").val();
  var password_acc = $("#password_acc").val();
  var detail = $("#detail").val();
  var img = $("#img")[0].files[0];
  var money = $("#money").val();
  var listImg = document.getElementById("list_img");
  var files = listImg.files;
  var formData = new FormData();
  for (let i = 0; i < files.length; i++) {
    formData.append("list_img", files[i]);
  }
  formData.append("group_id", group_id);
  formData.append("username_acc", username_acc);
  formData.append("password_acc", password_acc);
  formData.append("detail", detail);
  formData.append("img", img);
  formData.append("money", money);
  $.ajax({
    method: "POST",
    url: `http://localhost:8080/account/addAccount`,
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
$(document).on("click", "#deleteAccount", function () {
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var id = $(this).data("id");
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/account/delete/${id}`,
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
