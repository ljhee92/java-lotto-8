package lotto;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    
    private static final String PURCHASE_COUNT_MESSAGE = "%d개를 구매했습니다.";
    private static final String WINNING_STATISTICS_HEADER = "당첨 통계\n---";
    private static final String RESULT_FORMAT = "%s (%s원) - %d개";
    private static final String RETURN_RATE_FORMAT = "총 수익률은 %s%%입니다.";
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("#,###");
    private static final DecimalFormat RATE_FORMAT = new DecimalFormat("#,##0.0");

    public void printLottos(List<Lotto> lottos) {
        System.out.println(String.format(PURCHASE_COUNT_MESSAGE, lottos.size()));
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }

    public void printResults(Map<LottoResult, Integer> results, double returnRate) {
        System.out.println(WINNING_STATISTICS_HEADER);
        printResultByRank(LottoResult.FIFTH, results.get(LottoResult.FIFTH));
        printResultByRank(LottoResult.FOURTH, results.get(LottoResult.FOURTH));
        printResultByRank(LottoResult.THIRD, results.get(LottoResult.THIRD));
        printResultByRank(LottoResult.SECOND, results.get(LottoResult.SECOND));
        printResultByRank(LottoResult.FIRST, results.get(LottoResult.FIRST));
        printReturnRate(returnRate);
    }

    private void printResultByRank(LottoResult result, int count) {
        String prizeAmount = NUMBER_FORMAT.format(result.getPrize());
        System.out.println(String.format(RESULT_FORMAT, result.getDescription(), prizeAmount, count));
    }

    private void printReturnRate(double returnRate) {
        String formattedRate = RATE_FORMAT.format(returnRate);
        System.out.println(String.format(RETURN_RATE_FORMAT, formattedRate));
    }
}