<%-- 
    Document   : autentica
    Created on : May 31, 2014, 10:25:05 AM
    Author     : modesto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="Dao.AlunoDao, Dao.ProfessorDao, Modelo.Aluno, Modelo.Professor"%>

<c:set scope="request" var="matricula" value="${param.login}"/>
<c:set scope="request" var="senha" value="${param.senha}"/>

<c:choose>
    <c:when test="${param.tipo == 'administrador'}">
        <c:if test="${param.login == 'admin' && param.senha == '12345'}">
            <c:out value="Senha correta!"/>
            <%--<jsp:forward page="../Adm/index.jsp"/>--%>

            <%
                request.getSession(true);
                request.getSession().setAttribute("userType", "admin");
                response.sendRedirect("../Adm/index.jsp");
            %>
        </c:if>
        <c:out value="Senha incorreta!"></c:out>
    </c:when>
    <c:when test="${param.tipo == 'professor'}">
        <%
            ProfessorDao professorDao = new ProfessorDao();
            Integer mp = 0;
            try {
                mp = Integer.parseInt(request.getAttribute("matricula").toString());
            } catch (NumberFormatException ex) {
                response.sendRedirect("../erros/matricula_invalida.html");
            }
            Professor prof = professorDao.findByMatricula(mp);

            if (prof == null) {
                response.sendRedirect("../erros/erro_autenticacao.html");
            } else if (prof.getMatricula() == mp && prof.getSenha().equals(request.getAttribute("senha"))) {
                session.setAttribute("professor", prof);
                response.sendRedirect("../Prof/index.jsp");
            } else {
                response.sendRedirect("../erros/erro_autenticacao.html");
            }

        %>
    </c:when>
    <c:when test="${param.tipo == 'aluno'}">
        <%            AlunoDao alunoDao = new AlunoDao();
            Integer ma = 0;
            try {
                ma = Integer.parseInt(request.getAttribute("matricula").toString());
            } catch (NumberFormatException ex) {
                response.sendRedirect("../erros/matricula_invalida.html");
            }
            Aluno aluno = alunoDao.findByMatricula(ma);

            if (aluno == null) {
                //response.sendRedirect("../erros/erro_autenticacao.html");
            } else if (aluno.getMatricula() == ma && aluno.getSenha().equals(request.getAttribute("senha"))) {
                session.setAttribute("aluno", aluno);
                response.sendRedirect("../Alunos/index.jsp");
            }

        %>  
    </c:when>
</c:choose>