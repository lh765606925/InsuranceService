<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Dialog - Modal form</title>
  <!-- bootstrap -->
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-2.1.3.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script> 
  <!-- jquery-ui-bootstrap  -->
<%--<script src="${pageContext.request.contextPath}/jquery-ui-bootstrap/js/jquery-1.8.3.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/jquery-ui-bootstrap/js/jquery-ui-1.9.2.custom.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/background/resources/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>--%>
<%--<script src="${pageContext.request.contextPath}/background/resources/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>--%>
 

  
  <style>
    .tree {
    min-height:20px;
    padding:19px;
    margin-bottom:20px;
    background-color:#fbfbfb;
    border:1px solid #999;
    -webkit-border-radius:4px;
    -moz-border-radius:4px;
    border-radius:4px;
    -webkit-box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05);
    -moz-box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05);
    box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05)
}
.tree li {
    list-style-type:none;
    margin:0;
    padding:10px 5px 0 5px;
    position:relative
}
.tree li::before, .tree li::after {
    content:'';
    left:-20px;
    position:absolute;
    right:auto
}
.tree li::before {
    border-left:1px solid #999;
    bottom:50px;
    height:100%;
    top:0;
    width:1px
}
.tree li::after {
    border-top:1px solid #999;
    height:20px;
    top:25px;
    width:25px
}
.tree li span {
    -moz-border-radius:5px;
    -webkit-border-radius:5px;
    border:1px solid #999;
    border-radius:5px;
    display:inline-block;
    padding:3px 8px;
    text-decoration:none
}
.tree li.parent_li>span {
    cursor:pointer
}
.tree>ul>li::before, .tree>ul>li::after {
    border:0
}
.tree li:last-child::before {
    height:30px
}
.tree li.parent_li>span:hover, .tree li.parent_li>span:hover+ul li span {
    background:#eee;
    border:1px solid #94a0b4;
    color:#000
}
  </style>
  <script>
  $(function () {
	    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
	    $('.tree li.parent_li > span').on('click', function (e) {
	        var children = $(this).parent('li.parent_li').find(' > ul > li');
	        if (children.is(":visible")) {
	            children.hide('fast');
	            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
	        } else {
	            children.show('fast');
	            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
	        }
	        e.stopPropagation();
	    });
	});
  </script>
</head>
<body>
<div class="tree well">
    <ul>
        <li>
            <span><i class="icon-folder-open"></i> Parent</span> 
            <ul>
                <li>
                	<span><i class="icon-minus-sign"></i> Child</span> 
                    <ul>
                        <li>
	                        <span><i class="icon-leaf"></i> Grand Child</span> 
                        </li>
                    </ul>
                </li>
                <li>
                	<span><i class="icon-minus-sign"></i> Child</span> 
                    <ul>
                        <li>
	                        <span><i class="icon-leaf"></i> Grand Child</span> 
                        </li>
                        <li>
                        	<span><i class="icon-minus-sign"></i> Grand Child</span> 
                            <ul>
                                <li>
	                                <span><i class="icon-minus-sign"></i> Great Grand Child</span> 
		                            <ul>
		                                <li>
			                                <span><i class="icon-leaf"></i> Great great Grand Child</span> 
		                                </li>
		                                <li>
			                                <span><i class="icon-leaf"></i> Great great Grand Child</span> 
		                                </li>
		                             </ul>
                                </li>
                                <li>
	                                <span><i class="icon-leaf"></i> Great Grand Child</span> 
                                </li>
                                <li>
	                                <span><i class="icon-leaf"></i> Great Grand Child</span> 
                                </li>
                            </ul>
                        </li>
                        <li>
	                        <span><i class="icon-leaf"></i> Grand Child</span> 
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
        <li>
            <span><i class="icon-folder-open"></i> Parent2</span> 
            <ul>
                <li>
                	<span><i class="icon-leaf"></i> Child</span> 
		        </li>
		    </ul>
        </li>
    </ul>
</div>

<div class="tree">
    <ul>
        <li>
            <span><i class="icon-calendar"></i> 2013, Week 2</span>
            <ul>
                <li>
                	<span class="badge badge-success"><i class="icon-minus-sign"></i> Monday, January 7: 8.00 hours</span>
                    <ul>
                        <li>
	                        <a href=""><span><i class="icon-time"></i> 8.00</span> &ndash; Changed CSS to accomodate...</a>
                        </li>
                    </ul>
                </li>
                <li>
                	<span class="badge badge-success"><i class="icon-minus-sign"></i> Tuesday, January 8: 8.00 hours</span>
                    <ul>
                        <li>
	                        <span><i class="icon-time"></i> 6.00</span> &ndash; <a href="">Altered code...</a>
                        </li>
                        <li>
	                        <span><i class="icon-time"></i> 2.00</span> &ndash; <a href="">Simplified our approach to...</a>
                        </li>
                    </ul>
                </li>
                <li>
                	<span class="badge badge-warning"><i class="icon-minus-sign"></i> Wednesday, January 9: 6.00 hours</span>
                    <ul>
                        <li>
	                        <a href=""><span><i class="icon-time"></i> 3.00</span> &ndash; Fixed bug caused by...</a>
                        </li>
                        <li>
	                        <a href=""><span><i class="icon-time"></i> 3.00</span> &ndash; Comitting latest code to Git...</a>
                        </li>
                    </ul>
                </li>
                <li>
                	<span class="badge badge-important"><i class="icon-minus-sign"></i> Wednesday, January 9: 4.00 hours</span>
                    <ul>
                        <li>
	                        <a href=""><span><i class="icon-time"></i> 2.00</span> &ndash; Create component that...</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
        <li>
            <span><i class="icon-calendar"></i> 2013, Week 3</span>
            <ul>
                <li>
                	<span class="badge badge-success"><i class="icon-minus-sign"></i> Monday, January 14: 8.00 hours</span>
                    <ul>
                        <li>
	                        <span><i class="icon-time"></i> 7.75</span> &ndash; <a href="">Writing documentation...</a>
                        </li>
                        <li>
	                        <span><i class="icon-time"></i> 0.25</span> &ndash; <a href="">Reverting code back to...</a>
                        </li>
                    </ul>
                </li>
		    </ul>
        </li>
    </ul>
</div>
</body>
</html>