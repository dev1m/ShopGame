$(document).ready(function () {
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
    function formatNumberAndReplaceComma(number) {
      // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
      return Number(number).toLocaleString("en-US").replace(/,/g, ".");
    }
    function getUsername(user_id,callback) {
      var linkGet = `http://localhost:8080/user/getUserById/${user_id}`;
      
      $.ajax({
          method: "GET",
          url: linkGet,
          headers: {
              'Authorization': header,
          }
      })
      .done(function(response) {     
          var username =   response.data.username
          callback(username);
          
      });
  };
    $.ajax({
      method: "GET",
      url: `http://localhost:8080/account/getAccountSold`,
      headers: {
        Authorization: header,
      },
    }).done(function (msg) {
        $.each(msg.data, function (index, value) {
            var inputTime = value.date_submit;
            var dateObj = new Date(inputTime);
            var formattedTime = dateObj.toLocaleString();
            var inputTime1 = value.date_sell;
            var dateObj1 = new Date(inputTime1);
            var formattedTime1 = dateObj1.toLocaleString();
            var user_id = value.user_id;
            var html = `          
            <tr>
                <td>${index + 1}</td>
                <td>${value.id}</td>
                <td width="10%"><img width="100%" src="http://localhost:8080/account/file/${value.img}" /></td>
                <td>${value.username_acc}</td>
                `
                html+=`
                <td id="username_${value.id}">
                    ${getUsername(user_id, function(get) {
                      var username = document.getElementById(`username_${value.id}`);
                      username.textContent = get;
                    })}
                </td>
                <td>${formatNumberAndReplaceComma(value.money)} VNĐ</td>
                
                <td>${formattedTime}</td>
                <td>${formattedTime1}</td>c
                <td><span class="badge badge-success">ĐÃ BÁN</span></td>
                <td>
                    <a class="btn btn-primary" href="./edit_account.html?id=${value.id}">
                        <i class="fas fa-edit"></i>
                        <span>EDIT</span>
                    </a>
                </td>
            </tr>
            
            `
          $("#listAccountSold").append(html);
        })
    });
});
