namespace MyForstWebApplication.Models
{
  /// <summary>
  /// Результат выполнения операции
  /// </summary>
  public class OperationResult
  {
    /// <summary>
    /// Сообщение о результате
    /// </summary>
    public string Message { get; set; }

    public OperationResult(string message)
    {
      Message = message;
    }
  }
}