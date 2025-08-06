using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using MyForstWebApplication.Models;

namespace MyForstWebApplication.Controllers
{
  /// <summary>
  /// /// Контроллер для работы с прогнозами погоды
  /// </summary>
  [Route("api/[controller]")]
  [ApiController]
  [Produces("application/json")]
  public class WeatherForecastController : ControllerBase
  {

    private WeatherForecastHolder _weatherForecastHolder;
    private readonly ILogger<WeatherForecastController> _logger;

    public WeatherForecastController(
            WeatherForecastHolder weatherForecastHolder,
            ILogger<WeatherForecastController> logger)
    {
      _weatherForecastHolder = weatherForecastHolder;
      _logger = logger;
    }

    /// <summary>
    /// Добавить новый прогноз погоды
    /// </summary>
    /// <param name="date">Дата прогноза</param>
    /// <param name="temperatureC">Температура в градусах Цельсия</param>
    /// <response code="200">Прогноз успешно добавлен</response>
    /// <response code="400">Некорректные параметры запроса</response>
    /// <response code="500">Внутренняя ошибка сервера</response>
    [HttpPost("add")]
    [ProducesResponseType(StatusCodes.Status200OK)]
    [ProducesResponseType(StatusCodes.Status400BadRequest, Type = typeof(ErrorResponse))]
    [ProducesResponseType(StatusCodes.Status500InternalServerError, Type = typeof(ErrorResponse))]
    public IActionResult Add([FromQuery] DateTime date, [FromQuery] int temperatureC)
    {
      try
      {
        if (date == default)
          return BadRequest(new ErrorResponse("Не указана дата"));

        if (date > DateTime.Now.AddYears(1)
            || date < DateTime.Now.AddYears(-1))
          return BadRequest(new ErrorResponse("Некорректная дата"));

        _weatherForecastHolder.Add(date, temperatureC);
        return Ok(new OperationResult("Прогноз успешно добавлен"));
      }
      catch (Exception ex)
      {
        _logger.LogError(ex, "Ошибка при добавлении прогноза");
        return StatusCode(500, new ErrorResponse("Внутренняя ошибка сервера"));
      }
    }

    /// <summary>
    /// Обновить существующий прогноз погоды
    /// </summary>
    /// <param name="date">Дата прогноза</param>
    /// <param name="temperatureC">Температура в градусах Цельсия</param>
    /// <response code="200">Прогноз успешно обновлен</response>
    /// <response code="400">Некорректные параметры запроса</response>
    /// <response code="404">Прогноз не найден</response>
    /// <response code="500">Внутренняя ошибка сервера</response>

    [HttpPut("update")]
    [ProducesResponseType(StatusCodes.Status200OK)]
    [ProducesResponseType(StatusCodes.Status400BadRequest, Type = typeof(ErrorResponse))]
    [ProducesResponseType(StatusCodes.Status404NotFound, Type = typeof(ErrorResponse))]
    [ProducesResponseType(StatusCodes.Status500InternalServerError, Type = typeof(ErrorResponse))]
    public IActionResult Update([FromQuery] DateTime date, [FromQuery] int temperatureC)
    {
      try
      {
        if (date == default)
          return BadRequest(new ErrorResponse("Не указана дата"));

        var updated = _weatherForecastHolder.Update(date, temperatureC);
        return updated
            ? Ok(new OperationResult("Прогноз успешно обновлен"))
            : NotFound(new ErrorResponse("Прогноз не найден"));
      }
      catch (Exception ex)
      {
        _logger.LogError(ex, "Ошибка при обновлении прогноза");
        return StatusCode(500, new ErrorResponse("Внутренняя ошибка сервера"));
      }
    }

    /// <summary>
    /// Удалить прогноз погоды
    /// </summary>
    /// <param name="date">Дата прогноза</param>
    /// <response code="200">Прогноз успешно удален</response>
    /// <response code="400">Некорректные параметры запроса</response>
    /// <response code="404">Прогноз не найден</response>
    /// <response code="500">Внутренняя ошибка сервера</response>
    [HttpDelete("delete")]
    [ProducesResponseType(StatusCodes.Status200OK)]
    [ProducesResponseType(StatusCodes.Status400BadRequest, Type = typeof(ErrorResponse))]
    [ProducesResponseType(StatusCodes.Status404NotFound, Type = typeof(ErrorResponse))]
    [ProducesResponseType(StatusCodes.Status500InternalServerError, Type = typeof(ErrorResponse))]
    public IActionResult Delete([FromQuery] DateTime date)
    {
      try
      {
        if (date == default)
          return BadRequest(new ErrorResponse("Не указана дата"));

        var deleted = _weatherForecastHolder.Delete(date);
        return deleted
            ? Ok(new OperationResult("Прогноз успешно удален"))
            : NotFound(new ErrorResponse("Прогноз не найден"));
      }
      catch (Exception ex)
      {
        _logger.LogError(ex, "Ошибка при удалении прогноза");
        return StatusCode(500, new ErrorResponse("Внутренняя ошибка сервера"));
      }
    }

    /// <summary>
    /// Получить прогнозы погоды за период
    /// </summary>
    /// <param name="dateFrom">Начальная дата периода</param>
    /// <param name="dateTo">Конечная дата периода</param>
    /// <response code="200">Список прогнозов</response>
    /// <response code="400">Некорректные параметры запроса</response>
    /// <response code="500">Внутренняя ошибка сервера</response>
    [HttpGet("get")]
    [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(List<WeatherForecast>))]
    [ProducesResponseType(StatusCodes.Status400BadRequest, Type = typeof(ErrorResponse))]
    [ProducesResponseType(StatusCodes.Status500InternalServerError, Type = typeof(ErrorResponse))]
    public IActionResult Get([FromQuery] DateTime dateFrom, [FromQuery] DateTime dateTo)
    {
      try
      {
        if (dateFrom == default || dateTo == default)
          return BadRequest(new ErrorResponse("Не указаны даты периода"));

        if (dateFrom > dateTo)
          return BadRequest(new ErrorResponse("Начальная дата должна быть раньше конечной"));

        List<WeatherForecast> list = _weatherForecastHolder.Get(dateFrom, dateTo);
        return Ok(list);
      }
      catch (Exception ex)
      {
        _logger.LogError(ex, "Ошибка при получении прогнозов");
        return StatusCode(500, new ErrorResponse("Внутренняя ошибка сервера"));
      }
    }


  }
  /// <summary>
  /// Модель ответа об успешной операции
  /// </summary>
  public class OperationResult
  {
    public string Message { get; set; }
    public OperationResult(string message) => Message = message;
  }

  /// <summary>
  /// Модель ответа об ошибке
  /// </summary>
  public class ErrorResponse
  {
    public string Error { get; set; }
    public ErrorResponse(string error) => Error = error;
  }
}