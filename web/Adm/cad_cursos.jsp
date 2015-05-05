<%-- 
    Document   : cad_cursos
    Created on : May 31, 2014, 7:28:44 PM
    Author     : modesto
--%>

<%@page import="Modelo.Curso"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="description" content="">
                <meta name="author" content="">
                    <title>Bem vindo!!</title>

                    <!-- Bootstrap core CSS -->
                    <link href="../css/bootstrap.css" rel="stylesheet">

                        <!-- Add custom CSS here -->
                        <link href="../css/sb-admin.css" rel="stylesheet">
                            <link rel="stylesheet" href="../font-awesome/css/font-awesome.min.css">
                                <!-- Page Specific CSS -->
                                <link rel="stylesheet" href="../css/morris-0.4.3.min.css">
                                    </head>

                                    <body>
                                        <div id="wrapper">
                                            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                                                <div class="navbar-header">
                                                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"> <span class="sr-only">Navegação Alternativa</span> <span class="icon-bar"></span> </button>
                                                    <a class="navbar-brand" href="index.jsp">SGA</a> </div>

                                                <!-- Menus -->
                                                <div class="collapse navbar-collapse navbar-ex1-collapse">
                                                    <ul class="nav navbar-nav side-nav">
                                                        <li class="active" ><a href="index.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                                                        <li ><a href="../CursoController?action=list"><i class="fa fa-desktop"></i> Cursos</a></li>
                                                        <li><a href="../ProfessorController?action=list"><i class="fa fa-group"></i> Professores</a></li>
                                                            <%--<li><a href="cad_materias.html"><i class="fa fa-table"></i> Matérias</a></li>--%>
                                                        <li><a href="../MateriaController?action=list"><i class="fa fa-table"></i> Matérias</a></li>
                                                        <li ><a href="../AlunoController?action=list"><i class="fa fa-user"></i> Alunos</a></li>
                                                        <!--<li><a href="cad_notas.html"><i class="fa fa-folder-open"></i> Notas</a></li>
                                                        <li><a href="cad_faltas.html"><i class="fa fa-book"></i> Faltas</a></li>-->
                                                    </ul>
                                                    </li>
                                                    </ul>
                                                    <ul class="nav navbar-nav navbar-right navbar-user">
                                                        <li class="dropdown user-dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Nome Usuario <b class="caret"></b></a>
                                                            <ul class="dropdown-menu">
                                                                <li class="divider"></li>
                                                                <li><a href="../Logoff"><i class="fa fa-power-off"></i> Log Out</a></li>
                                                            </ul>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </nav>
                                            <div id="page-wrapper">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <h1>Cadastro <small>cursos</small></h1>
                                                        <ol class="breadcrumb">
                                                            <li><a href="index.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                                                            <li class="active"><i class="fa fa-edit"></i> Cadastro Cursos</li>
                                                        </ol>
                                                    </div>
                                                </div>
                                                <!-- /.row -->
                                                <div class="col-lg-4" style="width:50%">
                                                    <div class="panel panel-primary">
                                                        <div class="panel-heading">
                                                            <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Cadastro Cursos</h3>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div id="morris-chart-donut">
                                                                <form class="form-horizontal" action="../CursoController">
                                                                    <fieldset>
                                                                        <!-- Form Name -->
                                                                        <legend></legend>
                                                                        <!-- Text input-->
                                                                        <div class="control-group" style="margin-left:-80px;margin-top:-5px;" >
                                                                            <label class="control-label" for="textinput"  >Nome:</label>
                                                                            <div class="controls">
                                                                                <input class="form-control" name="nome">
                                                                            </div>
                                                                        </div>
                                                                        <p class="help-block"></p>
                                                                        <%--<button type="button" class="btn btn-primary" style="margin-left:18%;" >Cadastrar</button>--%>
                                                                        <input type="hidden" name="action" value="add"/>
                                                                        <input type="submit" class="btn btn-primary" style="margin-left: 18%;" value="Cadastrar"/>
                                                                        <%--<button type="button" class="btn btn-success">Editar</button>
                                                                        <button type="button" class="btn btn-danger">Excluir</button> --%>
                                                                    </fieldset>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4" style="width:50%">
                                                    <div class="panel panel-primary">
                                                        <div class="panel-heading">
                                                            <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Pesquisar Curso</h3>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div id="morris-chart-donut">
                                                                <form class="form-horizontal">
                                                                    <fieldset>
                                                                        <!-- Form Name -->
                                                                        <legend></legend>
                                                                        <!-- Text input-->
                                                                        <div class="control-group" style="margin-left:-80px;margin-top:-5px;" >
                                                                            <label class="control-label" for="textinput"  >Nome:</label>
                                                                            <div class="controls">
                                                                                <input class="form-control" name="nome">
                                                                            </div>
                                                                        </div>
                                                                        <p class="help-block"></p>
                                                                        <button type="button" class="btn btn-primary" style="margin-left:40%;" >Pesquisar</button>
                                                                    </fieldset>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4" style="width:100%">
                                                    <div class="panel panel-primary">
                                                        <div class="panel-heading">
                                                            <h3 class="panel-title"><i class="fa fa-money"></i> Lista de cursos</h3>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="table-responsive">
                                                                <table class="table table-bordered table-hover table-striped tablesorter">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>ID</th>
                                                                            <th>Curso</th>
                                                                                <%--<th>Matérias</th>--%>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:forEach items="${listaCurso}" var="curso">
                                                                            <tr>
                                                                                <td><c:out value="${curso.id}"/></td>
                                                                                <td><c:out value="${curso.nome}"/></td>
                                                                                <td><a href=<c:out value="../CursoController?action=delete&id=${curso.id}"/>>Deletar</a></td>
                                                                                <td><a href=<c:out value="../CursoController?action=edit&id=${curso.id}"/>>Editar</a></td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- JavaScript --> 
                                        <script src="../js/jquery-1.10.2.js"></script> 
                                        <script src="../js/bootstrap.js"></script> 
                                        <script src="../js/morris/chart-data-morris.js"></script> 
                                        <script src="../js/tablesorter/jquery.tablesorter.js"></script> 
                                        <script src="../js/tablesorter/tables.js"></script>

                                    </body>
                                    </html>

