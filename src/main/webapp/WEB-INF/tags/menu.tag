<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ attribute name="name" required="true" rtexprvalue="true" description="Name of the active menu"%>
<%@ tag pageEncoding="UTF-8" %>


<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value='/' htmlEscape='true' />" style="margin-top:13px;">
			</a>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">

			<ul class="nav navbar-nav">
				
				<petclinic:menuItem active="${name eq 'home'}" url="/" title="Pagina de Inicio">
					<span class="glyphicon glyphicon-home"></span>
					<span>Inicio</span>
				</petclinic:menuItem>

				<sec:authorize access="hasAnyAuthority('admin')">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<span class="glyphicon glyphicon-user"></span>
							<span>Usuarios</span>
							<span class="glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul class="dropdown-menu" style="background-color:#28B393;">
							<li>
								<p>
									<a href="/users/all" class="btn btn-block" style="color:white;">
										<span class="glyphicon glyphicon-user"></span> Listar</a>
								</p>
							</li>
							<li class="divider"></li>
							<li>
								<p>
									<a href="/users/find" class="btn btn-block" style="color:white;">
										<span class="glyphicon glyphicon-search"></span> Buscar</a>
								</p>
							</li>
						</ul>
					</li>

					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<span class="glyphicon glyphicon-stats"></span>
							<span>Estadísticas</span>
							<span class="glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul class="dropdown-menu" style="background-color:#28B393;">
							<li>
								<p>
									<a href="/stats" class="btn btn-block" style="color:white;">Generales</a>
								</p>
							</li>
							<li class="divider"></li>
							<li>
								<p>
									<a href="/stat" class="btn btn-block" style="color:white;">Mis estadísticas</a>
								</p>
							</li>
						</ul>
					</li>

					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<span class="glyphicon glyphicon-flag"></span>
							<span>Logros</span>
							<span class="glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul class="dropdown-menu" style="background-color:#28B393;">
							<li>
								<p>
									<a href="/logros" class="btn btn-block" style="color:white;">Generales</a>
								</p>
							</li>
							<li class="divider"></li>
							<li>
								<p>
									<a href="/logrosUsuario" class="btn btn-block" style="color:white;">Mis logros</a>
								</p>
							</li>
						</ul>
					</li>

					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<span class="glyphicon glyphicon-tower"></span>
							<span>Juegos</span>
							<span class="glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul class="dropdown-menu" style="background-color:#28B393;">
							<li>
								<p>
									<a href="/partidas" class="btn btn-block" style="color:white;">Generales</a>
								</p>
							</li>
							<li class="divider"></li>
							<li>
								<p>
									<a href="/partidas/${username}" class="btn btn-block" style="color:white;">Mis juegos</a>
								</p>
							</li>
						</ul>
					</li>
				</sec:authorize>

				<sec:authorize access="hasAnyAuthority('player')">
					<petclinic:menuItem active="${name eq 'usersF'}" url="/users/find" title="Buscar usarios">
						<span class="glyphicon glyphicon-search"></span>
						<span>Buscar usuarios</span>
					</petclinic:menuItem>

					<petclinic:menuItem active="${name eq 'userStats'}" url="/stat" title="Mis estadisticas">
							<span class="glyphicon glyphicon-stats"></span>
							<span>Mis estadísticas</span>
					</petclinic:menuItem>

					<petclinic:menuItem active="${name eq 'logrosListing'}" url="/logrosUsuario" title="Mis logros">
							<span class="glyphicon glyphicon-flag"></span>
							<span>Mis logros</span>
					</petclinic:menuItem>

					<petclinic:menuItem active="${name eq 'games'}" url="/partidas/${username}" title="Mis juegos">
							<span class="glyphicon glyphicon-tower"></span>
							<span>Mis juegos</span>
					</petclinic:menuItem>
				</sec:authorize>

				<petclinic:menuItem active="${name eq 'rules'}" url="/rules" title="Rules">
					<span class="glyphicon glyphicon-list-alt"></span>
					<span>Reglas</span>
				</petclinic:menuItem>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<petclinic:menuItem active="${name eq 'login'}" url="/login" title="Login">
						<span>Acceder</span>
					</petclinic:menuItem>

					<petclinic:menuItem active="${name eq 'register'}" url="/users/new" title="Register">
						<span>Registrarse</span>
					</petclinic:menuItem>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong>
							<span class="glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul class="dropdown-menu" style="background-color:#28B393;">
							<li>
								<p>
									<a href="/users/${username}" class="btn btn-block"
										style="color:white;">Mi Perfil</a>
								</p>
							</li>
							<li class="divider"></li>
							<li style="margin-top:2px;">
								<p class="text-left" style="text-align:center">
									<a href="<c:url value="/logout" />" class="btn btn-block" 
										style="color:white;">Salir</a>
								</p>
							</li>
						</ul>
					</li>
				</sec:authorize>				
			</ul>
		</div>
	</div>
</nav>
