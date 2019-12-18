# TranslatorWithTimeSprawozdanie z translatora

Zegary na pierwszej stronie sa zrobione w taki sposób: 
android:layout_marginTop="12dp"
android:format24Hour="hh:mm:ss a"
android:textSize="24sp"
android:timeZone="GMT"

Gdzie jeden ma GMT+1 (Polska), drugi GMT (London) i trzeci CET (Francja).
Translator na trugiej stronie zrobiony jest za pomocą button-ów, jednego EditText-a i jednego TextView.
Wartość z editText wysylana do oraz odbierana zostaje z serwera API za pomocą technologii JsonObjectRequest, JSONArray,JSONObject oraz Volley.
Wynik wyświetlany jest na ekranie po przez TextView. 
Funkcja wysylajaca do serwera informacje i pobierajaca odpowiedz:

private void tlumacz(String from, String to) {
String url = "https://api.mymemory.translated.net/get?q=";
EditText textToTranslateEditText = findViewById(R.id.editText);
String textToTranslate = textToTranslateEditText.getText().toString();
textToTranslate = textToTranslate.replaceAll(" ", "%20");
JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
(Request.Method.GET, url + textToTranslate + "&langpair=" + from + "|" + to, null, new Response.Listener<JSONObject>() {

@Override
  public void onResponse(JSONObject response) {
  try {
  JSONArray jsonArray = response.getJSONArray("matches");
  JSONObject match = jsonArray.getJSONObject(0);
  String result = match.getString("translation");
  result=result.replaceAll("&#39;","'");
  TextView widokOdpowiedzi = findViewById(R.id.textView8);
  widokOdpowiedzi.setText(result);
  } catch (JSONException e) {
  e.printStackTrace();
  }
}

}, new Response.ErrorListener() {



  @Override

  public void onErrorResponse(VolleyError error) {

  // TODO: Handle error

  Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

  }

});

Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);



}


W efekcie końcowym aplikacja tłumaczy tekst z języka angielskiego, polskiego oraz francuskiego, na angielski, polski oraz francuski.

Wykonanie:
Maciej N.

