<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="col-2">
        <a class="navbar-brand" href="/worldnews">WORLD NEWS PORTAL</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
    <div class="col-10">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <form class="mx-2 my-auto d-inline w-100" action = "/search" method="get">
                <div style="display:flex;">
                    <div class="col-10">
                        <input class="form-control" type="search" placeholder="Search" aria-label="Search" name = "key" value="<%=(request.getParameter("key") != null?request.getParameter("key"):"")%>">
                    </div>
                    <div class="col-2">
                        <button class="btn btn-outline-light" type="submit">Search</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</nav>



