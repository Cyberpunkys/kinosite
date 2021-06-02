<#import "macros/common.ftlh" as c>

<@c.page>
    <h1>Редактирование фильма</h1>
    <a href="/movies">Вернуться на главную</a>
    <#if movie??>
        <div class="row">
            <div class="col">
                <form class="mt-3" method="post" action="/movies/${movie.id}" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="disabledInputID" class="form-label">ID</label>
                        <input type="text" id="disabledInputID" class="form-control" placeholder="${movie.id}" disabled>
                    </div>
                    <div class="mb-3">
                        <label for="editName" class="form-label">Изменить название:</label>
                        <input type="text" class="form-control" id="editName" value="${movie.name}" name="name">
                    </div>
                    <div class="mb-3">
                        <label for="editYear" class="form-label">Изменить год:</label>
                        <input type="text" class="form-control" id="editYear" value="${movie.year?c}" name="year">
                    </div>
                    <div class="mb-3">
                        <label for="editDescription" class="form-label">Изменить описание:</label>
                        <textarea class="form-control" id="editDescription" rows="2" name="description">${movie.description}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="editPoster" class="form-label">Изменить постер</label>
                        <input class="form-control" type="file" id="editPoster" name="poster">
                    </div>
                    <button type="submit" class="btn btn-success">Сохранить</button>
                    <a href="/movies" class="btn btn-secondary" role="button">Отмена</a>
                </form>
            </div>
            <div class="col-4">
                <img src="/img/${movie.poster}" alt="Постер отсутствует." width="300px" height="469px">
            </div>
        </div>
        <form method="post" action="/movies/${movie.id}/delete" class="mt-5">
            <button type="submit" class="btn btn-danger">Удалить</button>
        </form>
    </#if>
</@c.page>