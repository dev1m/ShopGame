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
        url: `http://localhost:8080/user/getUserById/${id}`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        var html = `
                    
                    <div class="form-group row">
                    <label for="username" class="col-sm-2 col-form-label">Username</label>
                    <div class="col-sm-10">
                        <div class="form-line">
                            <input type="text" class="form-control" id="username"
                                value="${msg.data.username}" disabled>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-10">
                        <div class="form-line">
                            <input type="text" class="form-control" id="password"
                                value="${msg.data.password}">
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                        <div class="form-line">
                            <input type="text" class="form-control" id="email"
                                value="${msg.data.email}" disabled>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="money" class="col-sm-2 col-form-label">Số dư</label>
                    <div class="col-sm-10">
                        <div class="form-line">
                            <input type="number" class="form-control"  id="money"
                                value="${msg.data.money}" required>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="level" class="col-sm-2 col-form-label">Cấp độ</label>
                    <div class="col-sm-10">
                        <div class="form-line">
                        `
                        if(msg.data.level === "admin"){
                            html += `<input type="text" class="form-control" id="level" value="admin" placeholder="Cấp quyền admin thì ghi: admin">`
                        }
                        else{
                            html +=`<input type="text" class="form-control" id="level" value="user" placeholder="Cấp quyền admin thì ghi: admin">`
                        }
                        html +=`
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="banned" class="col-sm-2 col-form-label">Trạng thái</label>
                    <div class="col-sm-10">
                        <select class="custom-select" id="banned">
                        

                            `
                            if(msg.data.banned === 0){
                                html +=`<option value="0">Hoạt động</option>
                                <option value="1">Banned</option>`
                            }
                            else{
                                html +=`<option value="1">Banned</option>
                                <option value="0">Hoạt động</option>`
                            }
                            
                            
                           
                            html+=`
                        </select>
                        
                    </div>
                </div>
                <button type="button" id="btnSaveUser" class="btn btn-primary btn-block waves-effect">
                    <span>LƯU</span>
                </button>
                <a type="button" href="./user.html"
                    class="btn btn-danger btn-block waves-effect">
                    <span>TRỞ LẠI</span>
                </a>
        
        `
        $("#editUserDetail").append(html);

      });
      $.ajax({
        method: "GET",
        url: `http://localhost:8080/cashFlow/getByUserId/${id}`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        $.each(msg.data, function (index, value) {
            var inputTime = value.time;
                                            var dateObj = new Date(inputTime);
                                            var formattedTime = dateObj.toLocaleString();

            var html =  `
            
            <tr>
                                                <td>${index+1}</td>
                                                <td>${formatNumberAndReplaceComma(value.cash_old)}</td>
                                                <td>${formatNumberAndReplaceComma(value.cash_change)}</td>
                                                <td>${formatNumberAndReplaceComma(value.cash_new)}</td>
                                                <td>${formattedTime}<span class="badge badge-dark px-3"></span></td>
                                                <td>${value.cash_note}</td>
                                            </tr>
            
            
            `
            $("#listCashFlowUser").append(html);
        });


      });


});
$(document).on("click", "#btnSaveUser", function () {
    var url = new URL(window.location.href); // Lấy URL hiện tại
    var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
    var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
    
    //Dữ liệu
    var password_user = $("#password").val();
    var money_user = $("#money").val();
    var level_user = $("#level").val();
    var banned_user = $("#banned").val();
    $.ajax({
        method: "POST",
        url: `http://localhost:8080/user/update`,
        data: {
          id: id,
          level: level_user,
          money: money_user,
          banned: banned_user,
          password: password_user,
        },
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        if(msg.data){
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
$(document).on("click", "#btnCongTien", function () {
    var url = new URL(window.location.href); // Lấy URL hiện tại
    var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
    var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
    
    //Dữ liệu
     var money_string = $("#moneyCong").val();
     var money_cong = parseInt(money_string);
    var ghichu = $("#ghichuCong").val();
    $.ajax({
        method: "GET",
        url: `http://localhost:8080/user/getUserById/${id}`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        var username_user = msg.data.username;
        var money_user = msg.data.money;
        var money_new = money_user + money_cong;
            $.ajax({
                method: "POST",
                url: `http://localhost:8080/user/updateCashFlow`,
                data: {
                    username: username_user, 
                    newMoney: money_new   
                },
                headers: {
                    Authorization: header,
                },
            }).done(function (msg) {
                    $.ajax({
                            method: "POST",
                            url: `http://localhost:8080/cashFlow/addCashFlow`,
                            data: {
                                cashOld: money_user,
                                cashChange: money_cong,
                                cashNew: money_new,
                                cashNote: `ADMIN cộng tiền ('${ghichu}')`,
                                userId: id,
                            },
                            headers: {
                                Authorization: header,
                            },
                    }).done(function(msg){
                        if(msg.data){
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

});
$(document).on("click", "#btnTruTien", function () {
    var url = new URL(window.location.href); // Lấy URL hiện tại
    var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
    var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.
    var token = localStorage.getItem("token");
    var header = "Bearer " + token;
    
    //Dữ liệu
     var money_string = $("#moneyTru").val();
     var money_tru = parseInt(money_string);
    var ghichu = $("#ghichuTru").val();
    $.ajax({
        method: "GET",
        url: `http://localhost:8080/user/getUserById/${id}`,
        headers: {
          Authorization: header,
        },
      }).done(function (msg) {
        var username_user = msg.data.username;
        var money_user = msg.data.money;
        var money_new = money_user - money_tru;
            $.ajax({
                method: "POST",
                url: `http://localhost:8080/user/updateCashFlow`,
                data: {
                    username: username_user, 
                    newMoney: money_new   
                },
                headers: {
                    Authorization: header,
                },
            }).done(function (msg) {
                    $.ajax({
                            method: "POST",
                            url: `http://localhost:8080/cashFlow/addCashFlow`,
                            data: {
                                cashOld: money_user,
                                cashChange: money_tru,
                                cashNew: money_new,
                                cashNote: `ADMIN trừ tiền ('${ghichu}')`,
                                userId: id,
                            },
                            headers: {
                                Authorization: header,
                            },
                    }).done(function(msg){
                        if(msg.data){
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

});