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
</head>
<body>
<form id="storeForm" action="" >
<div id="p1" class="easyui-panel" title="基本信息" style="height: auto; padding: 20px; background: #F4F4F4;" collapsible="true">
  <table id="table1">
    <tr align="right">
      <td style="width: 15%;"></td>
      <td>客户姓名：<input name="customerName" id="customerNameId" class="easyui-validatebox"
                      style="width: 150px;" required='true'  missingMessage="客户姓名不能为空" validType ="chs" ></td>
      <td style="width: 30%;"></td>
      <td>借款金额：<input name="loanAmount" id="loanAmountId" class="easyui-validatebox"
                      style="width: 150px;" required='true'  missingMessage="借款金额不能为空" validType ="chs" ></td>
    </tr>
  </table>
  <a href="javascript:void(0);" class="btn btn-success save-msg" iconCls="icon-save" plain="true" onclick="saveDate();">&nbsp;&nbsp;提交&nbsp;&nbsp;</a>
</div>
</form>
</body>
</html>
<script>

  function saveDate() {
    $('#storeForm').form('submit', {
      url : '${path}/storeEvaluation/saveStoreEvaluation',
      onSubmit : function() {

      },
      success : function(data) {

      }
    });
  }
</script>