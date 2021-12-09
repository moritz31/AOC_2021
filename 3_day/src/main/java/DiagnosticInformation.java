public record DiagnosticInformation(String diagnostic) {
    public String getBitAtPosition(int position) {
        return String.valueOf(this.diagnostic.charAt(position));
    }
}
