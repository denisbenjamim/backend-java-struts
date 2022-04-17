<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset='utf-8'>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>
    <title>Importar Transação</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    
    <s:url var="contextoBootStrap" action="webjars/bootstrap/5.1.3" />

    <link rel='stylesheet' href='${contextoBootStrap}/css/bootstrap.min.css'>
</head>

<body class="container">
    <s:form action="/upload"  enctype="multipart/form-data" method="post">
        <div class="col col-sm-6 offset-sm-3 mt-5">
          <h1 class="text-center">IMPORTAR TRANSAÇÕES</h1>

          <div class="input-group">
              <input type="file" name="file" required accept=".csv" class="form-control" id="inputGroupFile02">
              
              <label class="input-group-text" for="inputGroupFile02">Upload</label>
          </div>

          <span class="text-secondary">Selecione o arquivo para realizar upload</span>

          <br>

          <button class="btn btn-primary mt-3">Importar</button>
          
          <s:if test="%{semImportacoes}">
            <hr>

            <h2 class="text-center">IMPORTAR REALIZADAS</h2>

            <table class="table table-striped">
              <thead>
                <tr>
                  <th>DATA TRANSAÇOES</th>

                  <th>DATA IMPORTAÇÕES</th>
                </tr>
              </thead>
              
              <tbody>
                <s:iterator value="importacoes">
                  <tr>
                    <td><s:property value="dataTransacaoFormtado"/></td>

                    <td><s:property value="dataHoraImportacaoFormtado"/></td>
                  </tr>
                </s:iterator>
              </tbody>
            </table>
          </s:if>
        </div>
    </s:form>
    
    
    <s:if test="%{semErroDeImportacao}">
      <div class="modal fade" id="modal-erro" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Não foi possivel realizar importação</h5>

                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>

              <div class="modal-body text-center">
                <p>${erroImportacao}</p>
              </div>

              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
              </div>

            </div>
          </div>
      </div>
    
        <script src="${contextoBootStrap}/js/bootstrap.min.js"></script>
        <script>
            const divModal = document.getElementById('modal-erro');

            if(divModal){
              const modalErroBootstrap = new bootstrap.Modal(divModal,  {keyboard: false}); 
              
              document.addEventListener('DOMContentLoaded', () => {
                divModal.addEventListener('hide.bs.modal', function(event){
                  window.history.pushState({}, document.title, window.location.pathname);
                })
                modalErroBootstrap.show();
              });
            } 
        </script>
     </s:if>  
</body>

</html>