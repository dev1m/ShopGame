$(document).ready(function(){
    var url = new URL(window.location.href); // Lấy URL hiện tại
    var params = new URLSearchParams(url.search); // Tạo một đối tượng URLSearchParams từ phần truy vấn của URL.
    var id = params.get("id"); // Lấy giá trị của tham số "id" từ URLSearchParams.

    var linkGroup = 'http://localhost:8080/group';
    var token = localStorage.getItem("token");
    var header = "Bearer "+token;
    function getCountOfAccount(groupId,callback) {
        var linkAccountCount = `http://localhost:8080/account/count/${groupId}`;
        
        $.ajax({
            method: "GET",
            url: linkAccountCount,
            headers: {
                'Authorization': header,
            }
        })
        .done(function(response) {     
            var totalCount =   response.data
            callback(totalCount);
            
        });
    };
    $.ajax({
        method: "GET",
        url: `${linkGroup}/get/${id}`,
        headers: {
            'Authorization':header,
        }
      })
        .done(function( msg ) {
            if(msg.success){
                $.each(msg.data,function(index,value){
                    if(value.display === "SHOW"){
                        var html = `
                            <div class="hover:shadow-lg col-span-4 sm:col-span-4 md:col-span-2 lg:col-span-2 xl:col-span-2 relative rounded border border-gray-300"
                            style="padding: 1px; padding: 1px;border: 3px solid #00afdb;">
                            <a href="account.html?id=${value.id}">
                                <img src="${linkGroup}/file/${value.img}"
                                    class="rounded-t h-28 md:h-48 w-full object-fill object-center lazyLoad" />
                                <div class="py-1">
                                    <div class="py-1 font-bold text-md px-1 truncate text-center uppercase"
                                        style="color: rgb(247, 176, 60);">
                                        ${value.title}
                                    </div>
                                    <ul class="px-2 flex items-center justify-center font-medium rounded-sm w-full font-medium text-gray-700">
                                        <span style="color: white;">Số tài khoản: <b id="countAccount_${value.id}">
                                        ${getCountOfAccount(value.id, function(totalCount) {
                                            var countAccount = document.getElementById(`countAccount_${value.id}`);
                                            countAccount.textContent = totalCount;
                                        })}
                                        </b>
                                        </span>
                                    </ul>
                                    <div class="mt-2 mb-2 px-2 py-1 flex items-center justify-center mt-9">
                                        <a class="focus:outline-none" href="account.html?id=${value.id}">
                                            <img src="./assets/storage/images/mua_ngay.png" class="lazyLoad isLoaded">
                                        </a>
                                    </div>
                                </div>
                            </a>
                            </div>
                    `;
                    $("#listGroup").append(html);
                    }
                })
                
            }
            
          
        });
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/category/get/title/${id}`,
            headers: {
                'Authorization':header,
            }
          })
            .done(function( msg ) {
                if(msg.data != null){   
                    var html = `${msg.data}`;
                    $("#titleCategory").append(html);
                      
                }        
                else{
                    var html = `Không tìm thấy dữ liệu !`;
                    $("#titleCategory").append(html);
                }      
            });
        
    

    
});