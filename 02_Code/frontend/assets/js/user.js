$(document).ready(function(){
    var usernameToken = localStorage.getItem("username");
    var token = localStorage.getItem("token");
    var header = "Bearer "+token;
    $.ajax({
        method: "GET",
        url: `http://localhost:8080/user/get/${usernameToken}`,
        headers: {
            'Authorization':header,
        }
      }).done(function( msg ) {
        
        function formatNumberAndReplaceComma(number) {
            // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
            return Number(number).toLocaleString('en-US').replace(/,/g, '.');
        }
            var html = `<tbody class="text-sm select-text">
            <tr class="v-border-hr-2 rounded-none border-b border-gray-200 py-10">
                <td class="v-account-text py-2 font-bold text-white">ID TÀI KHOẢN</td>
                <td class="v-table-title font-bold px-2 py-2 text-white uppercase"><span
                        class="py-1 px-3 bg-red-600 text-white rounded">${msg.data.id}</span>
                </td>
            </tr>
            <tr class="v-border-hr-2 rounded-none border-b border-gray-200 py-10">
                <td class="v-account-text py-2 font-bold text-white">TÊN TÀI KHOẢN</td>
                <td class="v-table-title px-2 py-2 text-white">${msg.data.username}</td>
            </tr>
            <tr class="v-border-hr-2 rounded-none border-b border-gray-200 py-10">
                <td class="v-account-text py-2 font-bold text-white">SỐ DƯ</td>
                <td class="px-2 py-2 text-white"><b class="text-red-500">${formatNumberAndReplaceComma(msg.data.money)} VNĐ</b></td>
            </tr>
            <tr class="v-border-hr-2 rounded-none border-b border-gray-200 py-10">
                <td class="v-account-text py-2 font-bold text-white">NHÓM TÀI KHOẢN</td>
                <td class="v-table-title px-2 py-2 text-white" id="nhomTaiKhoan">
                    
                </td>
            </tr>
        </tbody>`
        $("#tableUser").append(html);
        if(msg.data.level === 'admin'){
            var admin = "ADMIN";
            $("#nhomTaiKhoan").append(admin);
        }
        else{
            var user = "USER"
            $("#nhomTaiKhoan").append(user);
        } 
          
    }); 
});