package frc.robot.subsystems.vision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import edu.wpi.first.math.Pair;
import frc.lib.math.MathUtils;

public class VisionMonster {

    public static record CameraInfo(String position, Object cameraIO) {

    }
    
    private Map<String, List<CameraIO<?>>> cameras = new HashMap<>();
    private Map<String, Request> requests = new HashMap<>();
    private Map<String, Class<? extends CameraIO<?>>> interfaces = new HashMap<>();

    private int checkRelativePriority(String position, Class<?> clazz) {
        for (var i : cameras.get(position)) {
            if (clazz.isInstance(i)) {
                if (clazz.isAssignableFrom(interfaces.get(position))) {
                    return -1;
                } else {
                    return requests.get(position).priority;
                }
            }
        }
        
        return Integer.MAX_VALUE;
    }



    public VisionMonster() {
    }

    public void addSource(String position, CameraIO<?>... cameraIOs) {
        cameras.put(position, Arrays.asList(cameraIOs));
    }

    public boolean register(Request request) {
        for (var i : request.cameraIOs.keySet()) {
            var poses = request.cameraIOs.get(i);
        }

        var allPossibilities = MathUtils.cartesianProduct(new ArrayList<>(request.cameraIOs.values()).toArray(new String[][]{}));

        for (var possibility : allPossibilities) {
            //cry
        }

        return false;
    }

    // private <A extends CameraIO<?>> Pair<Integer, List<String>> minimizePriority(List<String> setPositions, String[][] optionalPositions, int startIndex, int currentMaxPriority, Class<A>[] myClazz) {
    //     if (optionalPositions.length > 0) {
    //         return new Pair<Integer, List<String>>(currentMaxPriority, setPositions);
    //     }

    //     int currentNextMaxPriority = Integer.MAX_VALUE;

    //     String currentPosition = "";

    //     for (var i : optionalPositions[startIndex]) {
    //         int nextPriority = checkRelativePriority(i, myClazz[startIndex]);
    //         int nextRestOfPriority = minimizePriority(setPositions , optionalPositions, startIndex + 1, currentMaxPriority, myClazz).getFirst();
    //         if (nextPriority <= currentNextMaxPriority) {
    //             currentNextMaxPriority = nextPriority;
    //             currentPosition = i;
    //         }
    //     }

    // }

    public static class Request {
        private boolean isValid = false;
        public int priority = 0;

        public Map<CameraIOFiller<?>, String[]> cameraIOs = new HashMap<>();

        public Request(int priority) {
            this.priority = priority;
        }

        public void close() {
            isValid = false;
        }

        public <T> CameraIO<T> add(String[] poses) {
            var cameraFiller =  new CameraIOFiller<T>(this::<T>runSomeCode);
            cameraIOs.put(cameraFiller, poses);
            return cameraFiller;
        }

        private <T> Optional<T> runSomeCode() {
            return Optional.empty();
        }

        public String[] getPositions() {
            return new String[] {};
        }

    }

    private static class CameraIOFiller<T> implements CameraIO<T> {
        private Supplier<Optional<T>> mySupplier;
        
        public Optional<T> updateInputs() {
            return mySupplier.get();
        }

        public CameraIOFiller(Supplier<Optional<T>> supplier) {
            mySupplier = supplier;
        }
    }
}
