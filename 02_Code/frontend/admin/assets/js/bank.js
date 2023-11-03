$(document).on("click", "#btnThemNganHang", function (event) {
  event.preventDefault();
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;

  var bank_name = $("#bank_name").val();
  var bank_number = $("#bank_number").val();
  var bank_fullname = $("#bank_fullname").val();
  var bank_note = $("#bank_note").val();
  var imgInput = document.getElementById("bank_logo");
  var formData = new FormData();
  formData.append("bank_number", bank_number);
  formData.append("bank_name", bank_name);
  formData.append("bank_fullname", bank_fullname);
  if (imgInput.files.length > 0) {
    formData.append("bank_logo", imgInput.files[0]);
  }
  formData.append("bank_note", bank_note);
  $.ajax({
    method: "POST",
    url: `http://localhost:8080/bank/addBank`,
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

  $.ajax({
    method: "GET",
    url: `http://localhost:8080/bank/getAll`,
    headers: {
      Authorization: header,
    },
  }).done(function (msg) {
    $.each(msg.data, function (index, value) {
      var html = `
        <tr>
            <td>${value.bank_name}</td>
            <td><img src="http://localhost:8080/bank/file/${value.bank_logo}" height="50px;" /></td>
            <td>${value.bank_fullname}</td>
            <td>${value.bank_number}</td>
            <td>${value.bank_note}</td>
            <td>
                <button class="btn btn-primary" id="btnEdit" data-bank_number="${value.bank_number}" data-bank_note="${value.bank_note}" data-bank_name="${value.bank_name}" data-bank_fullname="${value.bank_fullname}" data-id="${value.id}">
                    <i class="fas fa-edit"></i>
                    <span>EDIT</span>
                </button>

                <button  id="deleteBank" data-id="${value.id}" class="btn btn-warning">
                    <i class="fa-sharp fa-solid fa-trash"></i>
                    <span>DELETE</span>
                </button>
            </td>
        </tr>
        
        
        `;
      $("#listBank").append(html);
    });
  });
});
$(document).on("click", "#btnEdit", function () {
  var token = localStorage.getItem("token");
  var header = "Bearer " + token;

  var bank_number = $(this).data("bank_number");
  var bank_note = $(this).data("bank_note");
  var bank_name = $(this).data("bank_name");
  var bank_fullname = $(this).data("bank_fullname");
  var bank_id = $(this).data("id");

  $("#bank_name_edit").val(bank_name);
  $("#bank_number_edit").val(bank_number);
  $("#bank_fullname_edit").val(bank_fullname);
  $("#bank_note_edit").val(bank_note);
  $("#bank_number_edit").val(bank_number);
  $("#EditBank").modal("show");

  $("#btnSave").click(function () {
    var bank_name = $("#bank_name_edit").val();
    var bank_number = $("#bank_number_edit").val();
    var bank_fullname = $("#bank_fullname_edit").val();
    var bank_note = $("#bank_note_edit").val();
    var imgInput = document.getElementById("bank_logo_edit");
    var formData = new FormData();
    formData.append("id", bank_id);
    formData.append("bank_number", bank_number);
    formData.append("bank_name", bank_name);
    formData.append("bank_fullname", bank_fullname);
    formData.append("bank_note", bank_note);
    if (imgInput.files.length > 0) {
      formData.append("bank_logo", imgInput.files[0]);
    }
    $.ajax({
      method: "POST",
      url: `http://localhost:8080/bank/update`,
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
$(document).on("click", "#deleteBank", function () {
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
    var id = $(this).data("id");
    $.ajax({
      method: "GET",
      url: `http://localhost:8080/bank/delete/${id}`,
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