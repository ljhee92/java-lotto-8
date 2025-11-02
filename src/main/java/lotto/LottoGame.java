package lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoGame {
    
    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos;
    private final int purchaseAmount;

    public LottoGame(List<Lotto> lottos, int purchaseAmount) {
        this.lottos = lottos;
        this.purchaseAmount = purchaseAmount;
    }

    public Map<LottoResult, Integer> checkWinnings(WinningNumbers winningNumbers) {
        Map<LottoResult, Integer> results = new EnumMap<>(LottoResult.class);
        initializeResults(results);

        for (Lotto lotto : lottos) {
            LottoResult result = winningNumbers.match(lotto);
            if (result.isWinning()) {
                results.put(result, results.get(result) + 1);
            }
        }

        return results;
    }

    private void initializeResults(Map<LottoResult, Integer> results) {
        results.put(LottoResult.FIFTH, 0);
        results.put(LottoResult.FOURTH, 0);
        results.put(LottoResult.THIRD, 0);
        results.put(LottoResult.SECOND, 0);
        results.put(LottoResult.FIRST, 0);
    }

    public double calculateReturnRate(Map<LottoResult, Integer> results) {
        long totalPrize = 0;
        for (Map.Entry<LottoResult, Integer> entry : results.entrySet()) {
            totalPrize += (long) entry.getKey().getPrize() * entry.getValue();
        }
        return (double) totalPrize / purchaseAmount * 100;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}