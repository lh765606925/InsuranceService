<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">

<head>
    <meta charset="utf-8">
    <title>Bootstrap 搜索建议插件</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="这是一个基于 bootstrap 按钮式下拉菜单组件的搜索建议插件">
    <meta name="Keywords" content="Bootstrap Search Suggest,bootstrap,搜索建议插件"/>
    <meta name="author" content="lizhiwen@meizu.com">
    <link href="../../bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <h2>bootstrap combox 搜索建议插件</h2>
        <p>这是一个基于 bootstrap 按钮式下拉菜单组件的搜索建议插件.</p>
        <p>使用说明：<a href="README.html" target="_blank">README.html</a> <a href="https://github.com/lzwme/bootstrap-suggest-plugin" target="_blank">Github</a></p>
        <form action="index_submit" method="get" accept-charset="utf-8" role="form">
            <h3>测试(URL 获取)</h3>
            <p>配置了 data-id，非下拉菜单选择输入则背景色警告。</p>
            <div class="row">
                <div class="col-lg-6">
                    <div class="input-group">
                        <input type="text" class="form-control" id="test">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                            </ul>
                        </div>
                        <!-- /btn-group -->
                    </div>
                </div>
            </div>

            <h3>测试(URL 获取)</h3>
            <p>不展示下拉菜单按钮。</p>
            <div class="row">
                <div class="col-lg-6">
                    <div class="input-group">
                        <input type="text" class="form-control" id="testNoBtn">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                            </ul>
                        </div>
                        <!-- /btn-group -->
                    </div>
                </div>
            </div>

            <h3>测试(json 数据中获取)</h3>
            <p>默认启用空关键字检索。</p>
            <div class="row">
                <div class="col-lg-6">
                    <div class="input-group">
                        <input type="text" class="form-control" id="test_data">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                            </ul>
                        </div>
                        <!-- /btn-group -->
                    </div>
                </div>
            </div>

            <h3>百度搜索</h3>
            <p>支持逗号分隔多关键字</p>
            <div class="row">
                <div class="col-lg-6">
                    <div class="input-group" style="width: 300px;">
                        <input type="text" class="form-control" id="baidu">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                            </ul>
                        </div>
                        <!-- /btn-group -->
                    </div>
                </div>
            </div>

            <h3>淘宝搜索</h3>
            <p>支持逗号分隔多关键字</p>
            <div class="row">
                <div class="col-lg-6">
                    <div class="input-group" style="width: 400px;">
                        <input type="text" class="form-control" id="taobao">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                            </ul>
                        </div>
                        <!-- /btn-group -->
                    </div>
                </div>
            </div>

        </form>
    </div>
    <script src="../../bootstrap/js/jquery.min.js"></script>
    <script src="../../bootstrap/js/bootstrap.min.js"></script>
    <script src="../../bootstrap/js/bootstrap-suggest.js"></script>
    <script type="text/javascript">
    var testBsSuggest = $("#test").bsSuggest({
        //url: "/rest/sys/getuserlist?keyword=",
        url: "/back/producttype_test.action",
        /*effectiveFields: ["userName", "shortAccount"],
        searchFields: [ "shortAccount"],
        effectiveFieldsAlias:{userName: "姓名"},*/
        idField: "typeId",
        keyField: "name"
    }).on('onDataRequestSuccess', function (e, result) {
    	console.log('onDataRequestSuccess: ', result);
    }).on('onSetSelectValue', function (e, keyword) {
    	console.log('onSetSelectValue: ', keyword);
    }).on('onUnsetSelectValue', function (e) {
    	console.log("onUnsetSelectValue");
    });
    /**
     * 不显示下拉按钮
     */
    var testBsSuggest = $("#testNoBtn").bsSuggest({
        //url: "/rest/sys/getuserlist?keyword=",
        url: "data.json",
        /*effectiveFields: ["userName", "shortAccount"],
        searchFields: [ "shortAccount"],
        effectiveFieldsAlias:{userName: "姓名"},*/
        showBtn: false,
        idField: "typeId",
        keyField: "name"
    }).on('onDataRequestSuccess', function (e, result) {
    	console.log('onDataRequestSuccess: ', result);
    }).on('onSetSelectValue', function (e, keyword) {
    	console.log('onSetSelectValue: ', keyword);
    }).on('onUnsetSelectValue', function (e) {
    	console.log("onUnsetSelectValue");
    });

	//data 数据中获取
    var testdataBsSuggest = $("#test_data").bsSuggest({
        indexId: 2, //data.value 的第几个数据，作为input输入框的内容
        indexKey: 1, //data.value 的第几个数据，作为input输入框的内容
		data: {
			'value':[
				{'id':'0','word':'lzw','description':'http://lzw.me'},
				{'id':'1','word':'lzwme','description':'http://w.lzw.me'},
				{'id':'2','word':'meizu','description':'http://www.meizu.com'},
				{'id':'3','word':'flyme','description':'http://flyme.meizu.com'}
			],
			'defaults':'http://lzw.me'
		}
    });
    //百度搜索测试
    var baiduBsSuggest = $("#baidu").bsSuggest({
        allowNoKeyword: false, //是否允许无关键字时请求数据
        multiWord: true, //以分隔符号分割的多关键字支持
        separator: ",", //多关键字支持时的分隔符，默认为空格
        getDataMethod: "url", //获取数据的方式，总是从 URL 获取
        url: 'http://unionsug.baidu.com/su?p=3&t='+ (new Date()).getTime() +'&wd=', /*优先从url ajax 请求 json 帮助数据，注意最后一个参数为关键字请求参数*/
	    jsonp: 'cb',/*如果从 url 获取数据，并且需要跨域，则该参数必须设置*/
	    processData: function (json) {// url 获取数据时，对数据的处理，作为 getData 的回调函数
	    	var i, len, data = {value: []};
	        if (!json || !json.s || json.s.length === 0) {
	        	return false;
	        }

	        console.log(json);
	        len = json.s.length;

	        jsonStr = "{'value':[";
	        for (i = 0; i < len; i++) {
	        	data.value.push({
	        		word: json.s[i]
	        	});
	        }
	        data.defaults = 'baidu';

	        //字符串转化为 js 对象
	        return data;
	    }
    });
    //淘宝搜索建议测试
    var taobaoBsSuggest = $("#taobao").bsSuggest({
        indexId: 2, //data.value 的第几个数据，作为input输入框的内容
        indexKey: 1, //data.value 的第几个数据，作为input输入框的内容
        allowNoKeyword: false, //是否允许无关键字时请求数据
        multiWord: true, //以分隔符号分割的多关键字支持
        separator: ",", //多关键字支持时的分隔符，默认为空格
        getDataMethod: "url", //获取数据的方式，总是从 URL 获取
        effectiveFieldsAlias:{Id: "序号", Keyword: "关键字", Count: "数量"},
        showHeader: true,
		url: 'http://suggest.taobao.com/sug?code=utf-8&extras=1&q=', /*优先从url ajax 请求 json 帮助数据，注意最后一个参数为关键字请求参数*/
	    jsonp: 'callback',/*如果从 url 获取数据，并且需要跨域，则该参数必须设置*/
	    processData: function(json){// url 获取数据时，对数据的处理，作为 getData 的回调函数
	        var i, len, data = {value: []};

	        if(!json || !json.result || json.result.length == 0) {
	        	return false;
	        }

	        console.log(json);
	        len = json.result.length;

	        for (i = 0; i < len; i++) {
	        	data.value.push({
	        		"Id": (i + 1),
	        		"Keyword": json.result[i][0],
	        		"Count": json.result[i][1]
	        	});
	        }
	        console.log(data);
	        return data;
	    }
    });

    $("form").submit(function(e) {
        return false;
    });
    </script>
</body>

</html>