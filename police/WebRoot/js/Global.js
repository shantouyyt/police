
//var api_url_root = "http://api.eyuanchi.com/"

//var api_url = api_url_root + "interface/admin_api.ashx?actionname=login&actionparams={}"





var api_url_root = "http://localhost:5322/"
var api_url = api_url_root + "login.html";

var request_obj = null;

//初始化
function init() {
    request_obj = new Object();

    request_obj.ActionName = "";
    request_obj.ActionParams = ""; // 是一个JSON字符串
}

//请求接口
function reqAPI(url, type, req_obj, callback) {
    $.ajax({
        url: url,
        type: type,
        dataType: "json",
        data: { ActionName: req_obj.ActionName, ActionParams: req_obj.ActionParams },
        beforeSend: function (x) {
            //
        },
        error: function (x, e) {
            //
        },
        success: function (response) {
            if (!response.Status) {
                alert(response.Msg);
                return;
            }

            callback(response)
        }
    });

}


//登录返回方法
function responseAPI_Login(result_obj) {
    alert(result_obj.Msg);
}


init();
var loginObj = new Object();
loginObj.account = $("#Account").val();
loginObj.password = $("#Password").val();
loginObj.validNum = "123123";

request_obj.ActionName = "login";
request_obj.ActionParams = $.toJSON(loginObj);


reqAPI(api_url, "POST", request_obj, responseAPI_Login);

function btn_login() {
    //alert('aaa');

    reqAPI(api_url, "POST", request_obj, responseAPI_Login);
}