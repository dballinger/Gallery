<!DOCTYPE html>
<html>
<head>
    <title>Gallery</title>
    <script type="text/javascript" src="/scripts/lib/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="/scripts/lib/galleria-1.2.6.js"></script>
    <script type="text/javascript" src="/scripts/lib/galleria.history.js"></script>
    <script type="text/javascript" src="/scripts/Navigation.js"></script>
    <link href="/styles/gallery.css" type="text/css" rel="stylesheet"/>
    <link href="/styles/reset.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript">
        $(function () {
            new braxis.Navigation().initialise();
        });
    </script>
</head>
<body>
<div class="heading">
    <h1>${model.heading}</h1>
    <ul id="navigation">
        <li class="categories">
            <span class="navItem">Categories</span>
            <ul id="categories" class="hidden">
            <#list model.categories as category>
                <li<#if category.selected> class="selected"</#if>><a href="${category.url}">${category.name}</a></li>
            </#list>
            </ul>
        </li>
        <li class="about">
            <a class="navItem" href="/">About</a>
        </li>
    </ul>
</div>

<div id="about">
<#list model.about.paragraphs as paragraph>
    <p>${paragraph}</p>
</#list>
</div>


</body>
</html>