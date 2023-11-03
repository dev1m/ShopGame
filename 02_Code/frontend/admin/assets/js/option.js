$(document).on("click", "#btnSaveOption", function () {
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;

    // Khởi tạo danh sách keys và values
        var tenweb = $("#tenweb").val();
        var mota = $("#mota").val();
        var tukhoa = $("#tukhoa").val();
        var hotline = $("#hotline").val();
        var facebook = $("#facebook").val();
        var id_video_youtube = $("#id_video_youtube").val();
        var email_admin = $("#email_admin").val();
        var email = $("#email").val();
        var pass_email = $("#pass_email").val();
        var thongbao = $("#thongbao").val();
        var text_left_footer = $("#text_left_footer").val();
        var text_center_footer = $("#text_center_footer").val();
        var html_footer = $("#html_footer").val();
        var partner_id = $("#partner_id").val();
        var partner_key = $("#partner_key").val();

        // Tạo đối tượng JSON từ dữ liệu thu thập
        var data = {
            "tenweb": tenweb,
            "mota": mota,
            "tukhoa": tukhoa,
            "hotline": hotline,
            "facebook": facebook,
            "id_video_youtube": id_video_youtube,
            "email_admin": email_admin,
            "email": email,
            "pass_email": pass_email,
            "thongbao": thongbao,
            "text_left_footer": text_left_footer,
            "text_center_footer": text_center_footer,
            "html_footer": html_footer,
            "partner_key": partner_key,
            "partner_id": partner_id
        };

    $.ajax({
        method: "POST",
        url: `http://localhost:8080/option/update`,
        data: data,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        console.log(msg.data)
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
        }else {
            Swal.fire({
                icon: "error",
                title: "Lỗi",
                text: "Có lỗi xảy ra khi cập nhật!",
            });
        }
      });

});
$(document).ready(function() {
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;
  // Địa chỉ API của bạn để lấy giá trị dựa trên option_key
  const apiUrl = 'http://localhost:8080/option/getValue';

  // Danh sách các option_key bạn muốn lấy giá trị
  const optionKeys = ['tenweb', 'mota', 'tukhoa', 'hotline', 'thongbao', 'facebook', 'id_video_youtube', 'email_admin', 'email', 'pass_email', 'text_left_footer', 'text_center_footer', 'html_footer','partner_key', 'partner_id'];

  // Hàm để lấy giá trị từ API và cập nhật trường biểu mẫu tương ứng
  function fetchAndUpdateOptionValue(optionKey) {
      $.ajax({
        method: "GET",
        url: `${apiUrl}?option_key=${optionKey}`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        $(`#${optionKey}`).val(msg.data);
      })
  }
  // Lặp qua danh sách các option_key và gọi hàm fetchAndUpdateOptionValue cho mỗi option_key
  optionKeys.forEach(function(optionKey) {
      fetchAndUpdateOptionValue(optionKey);
  });

});