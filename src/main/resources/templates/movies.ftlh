<#import "macros/common.ftlh" as c>

<@c.page>
    <h1>Список фильмов</h1>

    <div class="container my-4">
        <div class="row">
            <div class="col">
                <p>
                    <button class="btn btn-dark" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseFindFilm"
                            aria-expanded="false" aria-controls="collapseFindFilm">
                        Поиск по фильмам
                    </button>
                </p>
                <div class="collapse" id="collapseFindFilm">
                    <div class="card card-body">
                        <form method="get" action="/movies">
                            <div class="row g-3">
                                <div class="col-sm-9">
                                    <label for="filmNameFilter" class="form-label">По названию:</label>
                                    <input type="text" class="form-control" id="filmNameFilter" name="filterName"
                                           value="${filterName!}"
                                           placeholder="Можно ввести часть названия, например: 'док'">
                                </div>
                                <div class="col-sm-3">
                                    <label for="filmYearFilter" class="form-label">По году:</label>
                                    <input type="text" class="form-control" id="filmYearFilter" name="filterYear"
                                           value="<#if filterYear??>${filterYear?c}</#if>">
                                </div>
                            </div>
                            <button class="btn btn-dark mt-3" type="submit">Найти</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col">
                <p>
                    <button class="btn btn-dark" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseAddFilm"
                            aria-expanded="false" aria-controls="collapseAddFilm">
                        Добавить фильм
                    </button>
                </p>
                <div class="collapse" id="collapseAddFilm">
                    <div class="card card-body">
                        <form method="post" action="/movies" enctype="multipart/form-data">
                            <div class="row g-3">
                                <div class="col-sm-9">
                                    <label for="filmName" class="form-label">Название</label>
                                    <input type="text" class="form-control" id="filmName" name="name">
                                </div>
                                <div class="col-sm-3">
                                    <label for="filmYear" class="form-label">Год выпуска</label>
                                    <input type="number" class="form-control" id="filmYear" name="year">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="descriptionTextarea" class="form-label">Описание</label>
                                <textarea class="form-control" id="descriptionTextarea" rows="2"
                                          name="description"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="formFile" class="form-label">Загрузите постер/обложку</label>
                                <input class="form-control" type="file" id="posterFile" name="poster">
                            </div>
                            <button type="submit" class="btn btn-dark">Создать</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <table class="table table-striped">
        <thead class="table-dark">
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Постер</th>
            <th scope="col">Год</th>
            <th scope="col">Описание</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list movies as movie>
            <tr>
                <td>${movie.name}</td>
                <td><img src="/img/${movie.poster}" alt="Картинко сломалась" height="350"></td>
                <td>${movie.year?c}</td>
                <td>${movie.description}</td>
                <th scope="row"><a href="/movies/${movie.id}">Изменить</a></th>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
