$(document).ready(function () {
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
    function formatNumberAndReplaceComma(number) {
      // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
      return Number(number).toLocaleString("en-US").replace(/,/g, ".");
    }
    $.ajax({
      method: "GET",
      url: `http://localhost:8080/user/getAllUser`,
      headers: {
        Authorization: header,
      },
    }).done(function (msg) {
        $.each(msg.data, function (index, value) {
            var html = `
                    <tr>
                        <td>${value.id}</td>
                        <td>${value.username}</td>               
                        <td>${value.email}</td>
                        <td>${formatNumberAndReplaceComma(value.money)}</td>
                        <td>
                    `
                    if(value.banned === 0){
                       html += `<span class="badge badge-success">Hoạt động</span>`
                    }
                    else{
                        html += `<span class="badge badge-danger">Banned</span>`
                    }
                    html +=`                    
                        </td>
                        <td>
                            <a type="button" href="./edit_user.html?id=${value.id}"
                                class="btn btn-primary"><i class="fas fa-edit"></i>
                                <span>EDIT</span></a>
                        </td>
                    </tr>
            
            
            `;
            $("#listUser").append(html);
        });
    })
      
});
  
  