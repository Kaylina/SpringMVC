<%--
  Created by IntelliJ IDEA.
  User: jingyan
  Date: 2016/8/17
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/pageContext.jsp"%>
<%@include file="../common/common.jsp"%>
<html>
<head>
    <title></title>
  <style type="text/css">
  .trace {
  color: #15428B;
  font-weight: bold;
  line-height: 23px;
  margin: 0;
  padding: 0;
  text-align: center;
  }


  </style>
  <script type="text/javascript">
    $(function() {
      // 跟踪
      $('.trace').click(graphTrace);
    });
  </script>
</head>
<body>

  <input id="dd" type="text" class="easyui-datebox" required="required">
  <a class="trace" href='#'  title="点击查看流程图">测试</a>
</body>
</html>
<script>
  function graphTrace() {
    var url="${path}/showImage?procDefId=PC1:1:4&procIstid=13";
    window.open(url,'_blank');
  }

</script>