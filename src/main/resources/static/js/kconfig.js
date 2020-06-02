/**
 * 通用配置
 * @type {{}}
 */
var config = {
    logEnable: true,    // 全局日志开关
    layui: {    // layui配置
        table: {
            cellMinWidth: 60
            ,request: {pageName : 'current', limitName : 'size'}
            ,response : {statusName : 'code', statusCode : 200, msgName : 'msg', dataName : 'data', countName: 'total'}
            ,height: 540
            ,defaultToolbar: ['filter', 'exports', 'print']  // 表格头部右侧按钮，若不需要，直接配置空数组
            ,page: {    //开启分页
                curr : 1,
                limit : 10
            }
        }
    },
    easyui: {   // easyui配置
        treegrid: {
            pagination: true,
            pageNumber: 1,
            pageSize: 10,
            pageList: [10, 20, 30, 40, 50],
            showHeader: true,
            nowrap: true,
            animate: true,
            scrollbarSize: 18,
            scrollOnSelect: false,
            fitColumns: true,
            checkbox: true,
            checkOnSelect: true,
            emptyMsg: '无数据',
            onBeforeLoad: function (row, param) {   // 若该方法被重写，需要把下面代码也要到重写方法中
                treeGridKit.handlePageParam(param);
            }
        }
    }
};

var req = {
    status: {
        ok: 200,
        fail: 400,
        other: 333
    },
    type: {
        get: 'GET',
        post: 'POST'
    }
};

/**
 * Api接口
 * @type {{}}
 */
var api = {
    homeView: BASE_WEB + 'hotel/index', // kvf-admin主页地址
    login: BASE_WEB + 'hotel/login',
    captchaUrl: BASE_WEB + 'hotel/captcha.jpg',

};

/**
 * layui 全局配置
 */
layui.config({
    base: BASE_WEB + 'static/plugins/lay-formselect/'
}).extend({
    formSelects: 'formSelects-v4'
});

layui.use(['layer', 'laytpl'], function () {
    var layer = layui.layer;
    layer.config({
        // anim: 1, //默认动画风格
        // skin: 'layui-layer-molv' //默认皮肤
    });
});

/**
 * ajax 全局拦截器
 */
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(xhr, textStatus){
        // 通过XMLHttpRequest取得响应头，sessionstatus，
        var sessionStatus = xhr.getResponseHeader("session-status");
        // log('sessionStatus=', sessionStatus);
        if(sessionStatus === "timeout") {
            // 如果超时就处理 ，指定要跳转的页面(比如登陆页)
            layer.confirm('未登录或登录超时，是否重新登录?', function () {
                parent.window.location.replace(api.login);
            });
        }
    },
    error: function(jqXHR, textStatus, errorThrown) {
        var errorMsg = jqXHR.responseJSON.message;
        log('errorMsg=', errorMsg);
        switch (jqXHR.status) {
            case(401):
                alert("未登录");
                break;
            case(403):
                alert("权限不足。403");
                break;
            case 404:
                alert('操作失败，没有此服务。404');
                break;
            case(408):
                alert("请求超时");
                break;
            case 500:
                alert('服务器错误(500)：' + errorMsg);
                break;
            case 504:
                alert('操作失败，服务器没有响应。504');
                break;
            default:
                alert("未知错误：" + errorMsg);
        }
    },
    success: function(r) {},
    statusCode:{
        200: function(r) {
            // log('statusCode 200 r=', r);
        }
    },
    beforeSend: function(jqXHR) {
        // log('beforeSend = ', jqXHR);
    }
});
