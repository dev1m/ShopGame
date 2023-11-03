$(document).ready(function () {
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var url = new URL(window.location.href); // Lấy URL hiện tại
  var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
  var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
  function formatNumberAndReplaceComma(number) {
    // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
    return Number(number).toLocaleString("en-US").replace(/,/g, ".");
  }
  function getUsername(user_id, callback) {
    var linkGet = `http://localhost:8080/user/getUserById/${user_id}`;

    $.ajax({
      method: "GET",
      url: linkGet,
      headers: {
        Authorization: header,
      },
    }).done(function (response) {
      var username = response.data.username;
      callback(username);
    });
  }
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/account/getAccountId/${id}`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    var user_id = msg.data.user_id;
    var date_sell = msg.data.date_sell;
    var dateObj = new Date(date_sell);
    var formattedTime = dateObj.toLocaleString();
    if (user_id !== null && date_sell !== null) {
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/user/getUserById/${user_id}`,
            headers: {
            Authorization: header,
            },
        }).done(function (response) {
            var username = response.data.username;
            var username_user = `<input type="text" value="${username}" class="form-control" readonly>`;
            $("#usernameUser").append(username_user);
            var date_sell = `<input type="text" value="${formattedTime}" class="form-control" readonly>`
            $("#dateSell").append(date_sell);

        });      
    }
    else{
        var username_user = `<input type="text" value="" class="form-control" readonly>`;
        $("#usernameUser").append(username_user);
        var date_sell = `<input type="text" value="" class="form-control" readonly>`
        $("#dateSell").append(date_sell);
    }
    var imgAccount = `<img width="100%" src="http://localhost:8080/account/file/${msg.data.img}" />`
    $("#imgAccount").append(imgAccount);
    var usernameAccount = `<input type="text" id="username_acc" name="username_acc" value="${msg.data.username_acc}" class="form-control" placeholder="Nhập tài khoản nick game" required>`
    $("#usernameAccount").append(usernameAccount);
    var passwordAccount = `<input type="text" placeholder="Nhập mật khẩu nick game" id="password_acc" name="password_acc" value="${msg.data.password_acc}" class="form-control" required>`
    $("#passwordAccount").append(passwordAccount);
    var detailAccount = `<textarea name="detail" id="detail" rows="6" class="form-control" required>${msg.data.detail}</textarea>`
    $("#detailAccount").append(detailAccount);
    var list_img = msg.data.list_img; 
    if (typeof list_img === "string") {
        list_img = list_img.split(",");
      }
    for (var i = 0; i < list_img.length; i++) {
        var imageURL = list_img[i];
        var listImgAccount = `<img src="http://localhost:8080/account/file/${imageURL}"  width="100px" style="padding: 5px;"/>`
        $("#listImgAccount").append(listImgAccount);
    }
    var moneyAccount = `<input type="number" id="money" name="money" value="${msg.data.money}" class="form-control" required>`
    $("#moneyAccount").append(moneyAccount);
    var buttonAccount = `
        <button type="submit" id="LuuTaiKhoan" name="LuuTaiKhoan" class="btn btn-primary btn-block">
            <span>LƯU NGAY</span>
        </button>
        <a type="button" href="./account.html?id=${msg.data.group_id}" class="btn btn-danger btn-block waves-effect">
            <span>TRỞ LẠI</span>
        </a>`
    $("#buttonAccount").append(buttonAccount)
  });
});
$(document).on("click", "#LuuTaiKhoan", function (event) {
  event.preventDefault();
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var url = new URL(window.location.href); // Lấy URL hiện tại
  var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
  var id_url = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
  var id_account = Number(id_url);

  var username_acc = $("#username_acc").val();
  var password_acc = $("#password_acc").val();
  var detail = $("#detail").val();
  var money = $("#money").val();
  var imgInput = document.getElementById('img');
  var listImg = document.getElementById("list_img");
  var files = listImg.files;
  var formData = new FormData();

  for (let i = 0; i < files.length; i++) {
    formData.append("list_img", files[i]);
  }
  formData.append("id", id_account);
  formData.append("username_acc", username_acc);
  formData.append("password_acc", password_acc);
  if (imgInput.files.length > 0) {
      formData.append("img", imgInput.files[0]);
  }
  formData.append("detail", detail);
  formData.append("money", money);
  $.ajax({
    method: "POST",
    url: `http://localhost:8080/account/update`,
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