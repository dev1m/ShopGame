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
      var valueTextArea = msg.data.description;
      $("#description").val(valueTextArea);

      var title_group = `<h1>CHỈNH SỬA NHÓM ${msg.data.title} </h1>`;
      $("#titleGroup").append(title_group);
      var img_group = `<img width="100%" src="http://localhost:8080/group/file/${msg.data.img}" />`;
      $("#imgGroup").append(img_group);
      var title_value = `
        <input type="hidden" class="form-control" id="id" name="id">
        <input type="text" id="title" name="title" value="${msg.data.title}" class="form-control" required>
      
      `;  
      $("#titleValue").append(title_value);
      var display_value = `
      <select class="form-control show-tick" id="display" name="display" required>
      `
      if(msg.data.display === "SHOW"){
        display_value+=`
        <option value="SHOW">SHOW</option>
        <option value="HIDE">HIDE</option>`
      }
      else{
        display_value+=`
        
        <option value="HIDE">HIDE</option>
        <option value="SHOW">SHOW</option>`
      }
      display_value+=`
      </select>`;
      $("#displayValue").append(display_value);

      var buttonGroup = `
      <button type="button" id="LuuChuyenMuc" name="LuuChuyenMuc" class="btn btn-primary btn-block">
            <span>LƯU NGAY</span>
      </button>
      <a type="button" href="./group_game.html?id=${msg.data.category_id}" class="btn btn-danger btn-block waves-effect">
        <span>TRỞ LẠI</span>
      </a>
      `
      $("#buttonGroup").append(buttonGroup)
    });

});
$(document).on("click", "#LuuChuyenMuc", function (event) {
    event.preventDefault();
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
    var url = new URL(window.location.href); // Lấy URL hiện tại
    var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
    var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
    var id_group = Number(id);
    $("#id").val(id_group);
    var formData = new FormData($("#groupGameFormEdit")[0]);
    $.ajax({
      method: "POST",
      url: `http://localhost:8080/group/update`,
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
          text: "Cập nhật  thành công!",
        }).then((result) => {
          setTimeout(() => {
            location.reload();
          }, 500);
        });
      }
    });
  });
  