package fylder.book.lib.http.tools

class ThrowableResponse : Throwable {

    var code: Int = 0

    constructor(code: Int, message: String) : super(message) {
        this.code = code
    }
}