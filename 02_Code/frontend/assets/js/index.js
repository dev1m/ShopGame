$(document).ready(function(){
    var linkCategory = 'http://localhost:8080/category';
    var linkCategoryCayThue = 'http://localhost:8080/category_caythue'
    var token = localStorage.getItem("token");
    var usernameToken = localStorage.getItem("username");
    var header = "Bearer "+token;
    function formatNumberAndReplaceComma(number) {
        // Định dạng số thành chuỗi và thay thế dấu phẩy bằng dấu chấm
        return Number(number).toLocaleString('en-US').replace(/,/g, '.');
    }
    function getCountOfGroups(categoryId,callback) {
        var linkGroupCount = `http://localhost:8080/group/count/${categoryId}`;
        
        $.ajax({
            method: "GET",
            url: linkGroupCount,
            headers: {
                'Authorization': header,
            }
        })
        .done(function(response) {     
            var totalCount =   response.data
            callback(totalCount);
            
        });
    };
    function getCountOfGroupCayThue(category_caythue_id,callback) {
        var linkGroupCayThueCount = `http://localhost:8080/group_caythue/count/${category_caythue_id}`;
        
        $.ajax({
            method: "GET",
            url: linkGroupCayThueCount,
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
        url: linkCategory,
        headers: {
            'Authorization':header,
        }
      })
        .done(function( msg ) {
            if(msg.success){
               
                $.each(msg.data,function(index,value){
                    if(value.display === "SHOW"){
                        var html = `
                        <div class="hover:shadow-lg col-span-4 sm:col-span-4 md:col-span-2 lg:col-span-2 xl:col-span-2 relative rounded border border-white-300"
                            style="padding: 1px; padding: 1px;border: 3px solid white;">
                        <a href="group.html?id=${value.id}">
                        <img src="${linkCategory}/file/${value.img}" class="rounded-t h-28 md:h-48 w-full object-fill object-center lazyLoad" />
                        <div class="py-1">
                        <div class="py-1 font-bold text-md px-1 truncate text-center uppercase" style="color: rgb(247, 176, 60);">
                            ${value.title}
                        </div>
                        <ul class="px-2 flex items-center justify-center font-medium rounded-sm w-full font-medium text-gray-700">
                        <span style="color: white;" >Số danh mục:
                        <b id="countGroup_${value.id}">
                            ${getCountOfGroups(value.id, function(totalCount) {
                                var countGroupElement = document.getElementById(`countGroup_${value.id}`);
                                countGroupElement.textContent = totalCount;
                            })}
                        </b>
                        </span>
                        </ul>
                        <div class="flex px-2 mt-2 justify-center">
                        </div>
                        <div class="mt-2 mb-2 px-2 py-1 flex items-center justify-center mt-9">
                        <a class="focus:outline-none" href="group.html?id=${value.id}">
                        <img src="./assets/storage/images/mua_ngay.png" class="lazyLoad isLoaded">
                        </a>
                        </div>
                        </div>
                        </a>
                        </div>
                    `;
                    $("#listCategory").append(html);
                    }
                })
                
            }
            
          
    });

    $.ajax({
        method: "GET",
        url: `http://localhost:8080/user/get/${usernameToken}`,
        headers: {
            'Authorization':header,
        }
    }).done(function(msg){
       
        var level = msg.data.level;
        if (level === 'admin') {
            var html = `<a href="./admin/index.html" class="lg:p-3 py-1 lg:py-2 px-2 lg:px-3 block">PANEL ADMIN</a>`;
            var html2 = ` <a href="./profile.html?id=${msg.data.id}" class="lg:ml-4 flex border px-3 h-8 border-gray-400 lg:rounded-full items-center text-white-800 font-bold justify-center lg:mb-0 mb-2 pointer-cursor">
            <span class="block"><i class="fa fa-user" aria-hidden="true"></i> ${msg.data.username} - ${formatNumberAndReplaceComma(msg.data.money)} </span></a>`;
            var html3 = `<a href="#" id="btn-logout"
            class="lg:ml-4 flex border px-3 h-8 border-gray-400 lg:rounded-full items-center text-white-800 font-bold justify-center lg:mb-0 mb-2 pointer-cursor"><span
                class="block"><i class="fa fa-sign-out" aria-hidden="true"></i> Đăng xuất</span></a>`;
            $("#panelAdmin").append(html);
            $("#inforUser").append(html2);
            $("#logout").append(html3);
        } 
        if(level !== 'admin'){
            var html2 = `
            <a href="./profile.html?id=${msg.data.id}" class="lg:ml-4 flex border px-3 h-8 border-gray-400 lg:rounded-full items-center text-white-800 font-bold justify-center lg:mb-0 mb-2 pointer-cursor">
            <span class="block"><i class="fa fa-user" aria-hidden="true"></i> ${msg.data.username} - ${formatNumberAndReplaceComma(msg.data.money)} </span></a>
            
            `;
            var html3 = `  <a href="#"ton " id="btn-logout"
            class="lg:ml-4 flex border px-3 h-8 border-gray-400 lg:rounded-full items-center text-white-800 font-bold justify-center lg:mb-0 mb-2 pointer-cursor"><span
                class="block"><i class="fa fa-sign-out" aria-hidden="true"></i> Đăng xuất</span></a>`;
            $("#inforUser").append(html2);
            $("#logout").append(html3);
        }
        
    });
    if(usernameToken === null ){
        var html1 = `<a href="login.html" 
        class="lg:ml-4 flex border px-3 h-8 border-gray-400 lg:rounded-full items-center text-white-800 font-bold justify-center lg:mb-0 mb-2 pointer-cursor">
        <span class="block"><i class="relative bx bxs-user mr-2"></i>Đăng nhập</span></a>`;
        var html2 = ` <a href="register.html" id="register"
        class="lg:ml-4 flex border px-3 h-8 border-gray-400 lg:rounded-full items-center text-white-800 font-bold justify-center lg:mb-0 mb-2 pointer-cursor">
        <span class="block"><i class="relative bx bxs-user-plus mr-2"></i>Đăng ký</span></a>`;
        $("#login").append(html1);
        $("#register").append(html2);
    };

    $.ajax({
        method: "GET",
        url: linkCategoryCayThue,
        headers: {
            'Authorization':header,
        }
      })
        .done(function( msg ) {
            if(msg.success){
                $.each(msg.data,function(index,value){
                    if(value.display === "SHOW"){
                        var html = `
                            <div class="hover:shadow-lg col-span-4 sm:col-span-4 md:col-span-2 lg:col-span-2 xl:col-span-2 relative rounded border border-white-300"
                            style="padding: 1px; padding: 1px;border: 3px solid white;">
                            <a href="group_caythue.html?id=${value.id}">
                                <img src="${linkCategoryCayThue}/file/${value.img}"
                                    class="rounded-t h-28 md:h-48 w-full object-fill object-center lazyLoad" />
                                <div class="py-1">
                                    <div class="py-1 font-bold text-md px-1 truncate text-center uppercase"
                                        style="color: rgb(247, 176, 60);">
                                    
                                        ${value.title}                                         
                                    </div>
                                    <ul class="px-2 flex items-center justify-center font-medium rounded-sm w-full font-medium text-gray-700">
                                        <span style="color: white;">Số dịch vụ:
                                        <b id="countGroupCayThue_${value.id}">
                                        ${getCountOfGroupCayThue(value.id, function(totalCount) {
                                            var countGroupElement = document.getElementById(`countGroupCayThue_${value.id}`);
                                            countGroupElement.textContent = totalCount;
                                        })}
                                    </b>
                                        </span>
                                    </ul>
                                    <div class="flex px-2 mt-2 justify-center">
                                    </div>
                                    <div class="mt-2 mb-2 px-2 py-1 flex items-center justify-center mt-9">
                                        <a class="focus:outline-none"
                                            href="group_caythue.html?id=${value.id}">
                                            <img src="./assets/storage/images/mua_ngay.png"
                                                class="lazyLoad isLoaded">
                                        </a>
                                    </div>
                                </div>
                            </a>
                        </div>
                `;
                    $("#listCategoryCayThue").append(html);
                    }
                })   
            }         
    });
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/user/topNap",
        headers: {
            'Authorization':header,
        }
      })
        .done(function( msg ) {
            if(msg.success){
                $.each(msg.data,function(index,value){
                    var html = `
                    <div class="v-list-top-card py-1 mt-2 md:py-2 px-1 md:px-3">
                                        
                    <div class="flex items-center justify-between px-2 py-1">
                        <div class="flex items-center">
                            <div class="v-star relative">
                                <i class="bx text-3xl text-red-500 bxs-star"></i>
                                <span class="absolute font-bold text-white" style="top: 4px; left: 11px;">
                                    ${index + 1}
                                </span>
                            </div>
                            <span class="ml-1 text-white w-full font-bold truncate"
                                style="max-width: 8rem;">
                                ${value.username}
                            </span>
                        </div>
                        <div class="font-bold text-lg">
                            <span class="bg-red-600 w-32 text-white rounded-sm text-center inline-block">
                            ${formatNumberAndReplaceComma(value.money)}
                                 <span
                                    class="text-xs"><small>VND</small></span>
                            </span>
                        </div>
                    </div>
                    
                    </div>

                    `;
                    $("#top").append(html);
                });
                
            }
            
          
    });

   
});
$(document).on('click', '#btn-logout', function(e) {
    e.preventDefault(); // Ngăn chặn chuyển hướng mặc định của thẻ <a>

    // Thực hiện các hành động đăng xuất ở đây
    // Ví dụ: Gửi yêu cầu đăng xuất đến máy chủ
    localStorage.clear();
    window.location.href = "./index.html"
});
