$(document).ready(function(){
    var url = new URL(window.location.href); // Lấy URL hiện tại
    var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
    var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.

    var linkGroupCayThue = 'http://localhost:8080/group_caythue';
    var token = localStorage.getItem("token");
    var header = "Bearer "+token;
    $.ajax({
        method: "GET",
        url: `${linkGroupCayThue}/get/${id}`,
        headers: {
            'Authorization':header,
        }
      }).done(function( msg ) {
        function formatNumberAndReplaceComma(number) {
            // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
            return Number(number).toLocaleString('en-US').replace(/,/g, '.');
        }
            if(msg.success){
                $.each(msg.data,function(index,value){
                    if(value.display === "SHOW"){
                        var title = `
                                              
                                <option value="${value.id}">${value.title} ( ${formatNumberAndReplaceComma(value.money)} VNĐ )</option>
                                `;
                    $("#dichvu").append(title);
                    }
                })
                
            }
            
          
        });
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/category_caythue/get/${id}`,
            headers: {
                'Authorization':header,
            }
          })
            .done(function( msg ) {
                var linkCategoryCayThue = "http://localhost:8080/category_caythue"
                if(msg.data != null){   
                    var title = `${msg.data.title}
                                `;
                    $("#titleCategory").append(title);
                    var img = `<img src="${linkCategoryCayThue}/file/${msg.data.img}" data-sizes="auto"
                    class="border border-gray-400 mb-2 w-full lazyLoad lazy" />`
                    
                    $("#imgCategory").append(img);
                      
                }        
                else{
                    var html = `Không tìm thấy dữ liệu !`;
                    $("#titleCategory").append(html);
                }      
            });
    
            
    
});
$(document).on("click", "#Submit", function (e) {
    var token = localStorage.getItem("token");
    var username = localStorage.getItem("username");
    var header = "Bearer " + token;
    var id_group = $("#dichvu").val();
    var tk = $("#tk").val();
    var mk = $("#mk").val();
    var ghichu = $("#ghichu").val();

    $("#Submit").html("ĐANG XỬ LÝ").prop("disabled", true);
    if (username == null) {
      Swal.fire({
        icon: "warning",
        title: "Thông báo",
        text: "Vui lòng đăng nhập để tiếp tục.",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Đăng nhập ngay",
      }).then((result) => {
          if(result.isConfirmed){
              window.location.href = "./login.html";
          }else{
              $("#Submit").html("ĐẶT HÀNG").prop("disabled", false);
          }
      });
    } else {
          Swal.fire({
          title: "Xác Nhận Đặt Hàng",
          text: "Bạn có đồng ý đặt hàng không ?",
          icon: "warning",
          showCancelButton: true,
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: "Đặt hàng ngay",
          }).then((result) => {
          if (result.isConfirmed) {
              $.ajax({
                  method: "GET",
                  url: `http://localhost:8080/user/get/${username}`,
                  headers: {
                      Authorization: header,
                  },
                  }).done(function (msg) {
                      var id_user = msg.data.id;
                      var money_user = msg.data.money;
                      
                      $.ajax({
                          method: "GET",
                          url: `http://localhost:8080/group_caythue/getGroupCayThue/${id_group}`,
                          headers: {
                              Authorization: header,
                          },
                      }).done(function (msg1) {
                              var money_group_cay_thue = msg1.data.money
                              if(money_group_cay_thue > money_user){
                                  Swal.fire({
                                      icon: "warning",
                                      title: "Thông báo",
                                      text: "Số dư không đủ vui lòng nạp thêm.",
                                  });
                              }
                              else{
                                  $.ajax({
                                      method: "POST",
                                      url: `http://localhost:8080/order/orderCayThue`,
                                      data: {
                                        group_caythue_id: id_group,
                                        money: money_group_cay_thue,
                                        username: tk,
                                        password: mk,
                                        status: "",
                                        note_user: ghichu,
                                        user_id: id_user
                                      },
                                      headers: {
                                          Authorization: header,
                                      },
                                      }).done(function (msg2) {
                                          if(msg2.data){
                                            $.ajax({
                                                method: "POST",
                                                url: `http://localhost:8080/user/updateCashFlow`,
                                                data: {
                                                    username: username,
                                                    newMoney: money_user - money_group_cay_thue
                                                },
                                                headers: {
                                                    Authorization: header,
                                                },
                                              });
                                            $.ajax({
                                              method: "POST",
                                              url: `http://localhost:8080/cashFlow/addCashFlow`,
                                              data: {
                                                  cashOld: money_user,
                                                  cashChange: money_group_cay_thue,
                                                  cashNew: money_user - money_group_cay_thue,
                                                  cashNote: `Đặt hàng gói (# ${id_group})`,
                                                  userId: id_user
                                              },
                                              headers: {
                                                  Authorization: header,
                                              },
                                              });
                                              Swal.fire({
                                                  icon: "success",
                                                  title: "Thành công",
                                                  text: "Đặt hàng thành công!",
                                              }).then((result) => {
                                                  setTimeout(() => {
                                                    window.location.href = "./history_caythue.html";
                                                  }, 1000);
                                                });
                                          }
                                          else{
                                              Swal.fire({
                                                  icon: "error",
                                                  title: "Thất bại",
                                                  text: "Đặt hàng thất bại!",
                                              });
                                          }
                                      });
                              }
                      });   
              });
                     
          } else {
              $("#Submit").html("ĐẶT HÀNG").prop("disabled", false);
          }
      });
    }
  });