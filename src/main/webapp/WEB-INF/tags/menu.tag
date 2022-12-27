<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ attribute name="name" required="true" rtexprvalue="true" description="Name of the active menu"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value='/' htmlEscape='true' />">
			</a>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">

			<ul class="nav navbar-nav">

				<petclinic:menuItem active="${name eq 'home'}" url="/" title="Home page">
					<span class="glyphicon glyphicon-home"></span>
					<span>Home</span>
				</petclinic:menuItem>

				<sec:authorize access="hasAnyAuthority('admin')">
					<petclinic:menuItem active="${name eq 'usersA'}" url="/users/all" title="Users">
						<span class="glyphicon glyphicon-user"></span>
						<span>Users</span>
					</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<petclinic:menuItem active="${name eq 'usersF'}" url="/users/find" title="Find users">
						<span class="glyphicon glyphicon-search"></span>
						<span>Find users</span>
					</petclinic:menuItem>

					<petclinic:menuItem active="${name eq 'userStats'}" url="/stats/${username}" title="My stats">
							<span class="glyphicon glyphicon-stats"></span>
							<span>My stats</span>
					</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="hasAnyAuthority('admin')">
					<petclinic:menuItem active="${name eq 'stats'}" url="/stats" title="Stats">
						<span class="glyphicon glyphicon-stats"></span>
						<span>Stats</span>
					</petclinic:menuItem>

					<petclinic:menuItem active="${name eq 'games'}" url="/partidas" title="Games">
						<span class="glyphicon glyphicon-tower"></span>
						<span>Games</span>
					</petclinic:menuItem>
				</sec:authorize>

				<petclinic:menuItem active="${name eq 'rules'}" url="/rules" title="Rules">
					<span class="glyphicon glyphicon-list-alt"></span>
					<span>Rules</span>
				</petclinic:menuItem>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<petclinic:menuItem active="${name eq 'login'}" url="/login" title="Login">
						<span>Login</span>
					</petclinic:menuItem>

					<petclinic:menuItem active="${name eq 'register'}" url="/users/new" title="Register">
						<span>Register</span>
					</petclinic:menuItem>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong>
							<span class="glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<p>
									<a href="/users/${username}" class="btn btn-block"
										style="background-color:#28B393;color:white;">My Profile</a>
								</p>
							</li>
							<li style="margin-top:2px;">
								<p class="text-left" style="text-align:center">
									<a href="<c:url value="/logout" />" class="btn btn-block" 
										style="background-color:#28B393;color:white;">Logout</a>
								</p>
							</li>
						</ul>
					</li>
				</sec:authorize>				
			</ul>
		</div>
	</div>
</nav>
