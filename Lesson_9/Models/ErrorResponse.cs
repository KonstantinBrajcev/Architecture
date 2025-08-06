namespace MyForstWebApplication.Models
{
  /// <summary>
  /// Ответ об ошибке
  /// </summary>
  public class ErrorResponse
  {
    /// <summary>
    /// Сообщение об ошибке
    /// </summary>
    public string Error { get; set; }

    public ErrorResponse(string error)
    {
      Error = error;
    }
  }
}