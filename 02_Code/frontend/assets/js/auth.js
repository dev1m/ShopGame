$(document).ready(function () {
  $("#Login").click(function () {
    var username = $("#username").val();
    var password = $("#password").val();
    var UserNotFound = "UserNotFound";
    var PasswordMismatch = "PasswordMismatch";
    if (username.trim() === "" || password.trim() === "") {
      Swal.fire({
        icon: "error",
        title: "Lỗi",
        text: "Vui lòng điền đầy đủ thông tin tài khoản và mật khẩu.",
      });
      return;
    }
    $.ajax({
      method: "POST",
      url: "http://localhost:8080/auth/login",
      data: {
        username: username,
        password: password,
      },
    }).done(function (msg) {
      if (msg.success) {
        localStorage.setItem("token", msg.data);
        localStorage.setItem("username", username);
        Swal.fire({
          icon: "success",
          title: "Thành công",
          text: "Đăng nhập thành công",
        }).then((result) => {
          setTimeout(() => {
            window.location.href = "./index.html";
          }, 1000);
        });
      }
      if (msg.data == UserNotFound) {
        Swal.fire({
          icon: "error",
          title: "Lỗi",
          text: "Tài khoản username không tồn tại trong hệ thống.",
        });
      }
      if (msg.data == PasswordMismatch) {
        Swal.fire({
          icon: "error",
          title: "Lỗi",
          text: "Sai mật khẩu vui lòng kiểm tra lại.",
        });
      }
      if (msg.data == false) {
        Swal.fire({
          icon: "error",
          title: "Lỗi",
          text: "Đăng nhập thất bại.",
        });
      }
    });
  });

  $("#Register").click(function () {
    var username = $("#username").val();
    var password = $("#password").val();
    var email = $("#email").val();
    var rePassword = $("#repassword").val();
    var userData = {
        username: username,
        password: password,
        email: email
    }
    if (
      username.trim() === "" ||
      password.trim() === "" ||
      email.trim() == "" ||
      rePassword.trim() == ""
    ) {
      Swal.fire({
        icon: "error",
        title: "Lỗi",
        text: "Vui lòng điền đầy đủ thông tin.",
      });
      return;
    }
    if (password.length < 3) {
      Swal.fire({
        icon: "error",
        title: "Lỗi",
        text: "Vui lòng nhập mật khẩu dài hơn 3 ký tự.",
      });
    }
    if (password != rePassword) {
      Swal.fire({
        icon: "error",
        title: "Lỗi",
        text: "Mật khẩu nhập lại không trùng khớp.",
      });
    }

    var UserExists = "UserExists";
    var EmailInvalid = "EmailInvalid";
    $.ajax({
      method: "POST",
      url: "http://localhost:8080/auth/register",
      contentType: "application/json",
      data: JSON.stringify(userData),
    }).done(function (msg) {
      if (msg.success) {
        Swal.fire({
          icon: "success",
          title: "Thành công",
          text: "Đăng ký thành công",
        }).then((result) => {
          setTimeout(() => {
            window.location.href = "./login.html";
          }, 1000);
        });
      }
      if (msg.data == UserExists) {
        Swal.fire({
          icon: "error",
          title: "Lỗi",
          text: "Tài khoản username đã tồn tại trong hệ thống.",
        });
      }
      if (msg.data == EmailInvalid) {
        Swal.fire({
          icon: "error",
          title: "Lỗi",
          text: "Nhập sai định dạng gmail vui lòng nhập đúng định dạng.",
        });
      }
      if (msg.data == false) {
        Swal.fire({
          icon: "error",
          title: "Lỗi",
          text: "Đăng ký thất bại.",
        });
      }
    });
  });
});
$(document).on("click", "#DoiMatKhau", function () {
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  var username = localStorage.getItem("username");
  var newPassword = $("#password").val();
  var rePassword = $("#repassword").val();
  if(newPassword !== rePassword){
    Swal.fire({
      icon: "error",
      title: "Lỗi",
      text: "Mật khẩu nhập lại chưa trùng khớp!",
    });
  }
  else{
    $.ajax({
      method: "POST",
      url: `http://localhost:8080/auth/changePassword`,
      data: {
          username: username,
          newPassword: newPassword
      },
      headers: {
          Authorization: header,
      },
    }).done(function (msg) {
      if(msg.data){
        Swal.fire({
          icon: "success",
          title: "Thành công",
          text: "Đổi mật khẩu thành công",
        }).then((result) => {
          setTimeout(() => {
            window.location.href = "./index.html";
          }, 1000);
        });
      }


    });
  }
  
})


$(document).on("click", "#ForgotPassword", function () {
  var email = $("#email").val();
  var gmailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
  if(gmailRegex.test(email)){
    $.ajax({
      method: "POST",
      url: `http://localhost:8080/auth/reset-password`,
      data: {
        email: email
      }
  
    }).done(function (msg) {
      if(msg.data){
        Swal.fire({
          icon: "success",
          title: "Thành công",
          text: "Khôi phục mật khẩu thành công,vui lòng kiểm tra gmail!",
        }).then((result) => {
          setTimeout(() => {
            window.location.href = "./login.html";
          }, 1000);
        });
      }
      else{
        Swal.fire({
          icon: "error",
          title: "Thất bại",
          text: "Email của bạn chưa được đăng kí ở hệ thống chúng tôi!",
        })
      }
    });
  }else{
    Swal.fire({
      icon: "error",
      title: "Thất bại",
      text: "Email của bạn chưa đúng định dạng!",
    })
  }
  
});