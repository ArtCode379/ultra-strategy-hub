package ultraconcierge.management.ultrastrategyhub.data.repository

import java.time.LocalTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ultraconcierge.management.ultrastrategyhub.data.model.ServiceModel

class ServiceRepository {
    private val services: List<ServiceModel> =
        listOf(
            ServiceModel(
                1,
                "Executive Strategy Session",
                "A focused leadership session to clarify priorities, align decisions, and turn ambition into a practical 90-day roadmap.",
                180.0,
                listOf(LocalTime.of(9, 0), LocalTime.of(13, 30), LocalTime.of(16, 0)),
                "https://images.unsplash.com/photo-1552664730-d307ca884978?auto=format&fit=crop&w=1200&q=85",
                "Strategic Planning",
                90,
                listOf(
                    "Pre-session leadership questionnaire",
                    "Facilitated strategic alignment",
                    "90-day action roadmap",
                    "Executive summary within 48 hours",
                ),
            ),
            ServiceModel(
                2,
                "Business Transformation Roadmap",
                "Build a sequenced transformation plan connecting commercial goals, operating capabilities, people, and technology.",
                320.0,
                listOf(LocalTime.of(10, 0), LocalTime.of(14, 0)),
                "https://images.unsplash.com/photo-1521737711867-e3b97375f902?auto=format&fit=crop&w=1200&q=85",
                "Strategic Planning",
                120,
                listOf(
                    "Current-state assessment",
                    "Transformation workstreams",
                    "Risk and dependency map",
                    "Board-ready roadmap",
                ),
            ),
            ServiceModel(
                3,
                "Organisational Structure Audit",
                "Identify unclear ownership, duplicated effort, and structural bottlenecks with evidence-based recommendations.",
                260.0,
                listOf(LocalTime.of(9, 30), LocalTime.of(15, 0)),
                "https://images.unsplash.com/photo-1522071820081-009f0129c71c?auto=format&fit=crop&w=1200&q=85",
                "People & Organisation",
                90,
                listOf(
                    "Role and reporting review",
                    "Decision-rights mapping",
                    "Span-of-control analysis",
                    "Target structure recommendations",
                ),
            ),
            ServiceModel(
                4,
                "Leadership Team Effectiveness",
                "Strengthen leadership rhythms, accountability, and collaboration around the outcomes that matter most.",
                210.0,
                listOf(LocalTime.of(11, 0), LocalTime.of(16, 30)),
                "https://images.unsplash.com/photo-1551836022-d5d88e9218df?auto=format&fit=crop&w=1200&q=85",
                "People & Organisation",
                90,
                listOf(
                    "Team effectiveness diagnostic",
                    "Leadership charter",
                    "Meeting rhythm redesign",
                    "Accountability actions",
                ),
            ),
            ServiceModel(
                5,
                "Process Optimisation Review",
                "Map a critical workflow, expose delay and rework, and design a leaner process with measurable controls.",
                240.0,
                listOf(LocalTime.of(9, 0), LocalTime.of(14, 30)),
                "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?auto=format&fit=crop&w=1200&q=85",
                "Process Optimisation",
                120,
                listOf(
                    "End-to-end process map",
                    "Waste and bottleneck analysis",
                    "Future-state workflow",
                    "Implementation scorecard",
                ),
            ),
            ServiceModel(
                6,
                "Operating Model Design",
                "Define how capabilities, governance, processes, and technology work together to deliver your strategy.",
                350.0,
                listOf(LocalTime.of(10, 30), LocalTime.of(15, 30)),
                "https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?auto=format&fit=crop&w=1200&q=85",
                "Process Optimisation",
                120,
                listOf(
                    "Capability assessment",
                    "Governance blueprint",
                    "Service delivery model",
                    "Transition priorities",
                ),
            ),
            ServiceModel(
                7,
                "Change Readiness Assessment",
                "Understand stakeholder sentiment, adoption risks, and the interventions needed before a major change begins.",
                190.0,
                listOf(LocalTime.of(9, 30), LocalTime.of(13, 0)),
                "https://images.unsplash.com/photo-1524758631624-e2822e304c36?auto=format&fit=crop&w=1200&q=85",
                "Change Management",
                75,
                listOf(
                    "Stakeholder analysis",
                    "Readiness pulse",
                    "Adoption risk map",
                    "Change action plan",
                ),
            ),
            ServiceModel(
                8,
                "Performance KPI Framework",
                "Create a balanced set of operational measures that connects team activity to strategic outcomes.",
                220.0,
                listOf(LocalTime.of(11, 30), LocalTime.of(16, 0)),
                "https://images.unsplash.com/photo-1551288049-bebda4e38f71?auto=format&fit=crop&w=1200&q=85",
                "Performance",
                90,
                listOf(
                    "Outcome and driver tree",
                    "KPI definitions",
                    "Reporting cadence",
                    "Dashboard blueprint",
                ),
            ),
            ServiceModel(
                9,
                "Growth Opportunity Workshop",
                "Evaluate market opportunities and create a prioritised portfolio of growth bets with clear test plans.",
                275.0,
                listOf(LocalTime.of(10, 0), LocalTime.of(14, 0)),
                "https://images.unsplash.com/photo-1556761175-b413da4baf72?auto=format&fit=crop&w=1200&q=85",
                "Growth",
                120,
                listOf(
                    "Opportunity landscape",
                    "Value-versus-effort scoring",
                    "Growth hypothesis design",
                    "Experiment backlog",
                ),
            ),
            ServiceModel(
                10,
                "Operational Efficiency Diagnostic",
                "Benchmark performance and uncover practical savings across capacity, handoffs, and management routines.",
                290.0,
                listOf(LocalTime.of(9, 0), LocalTime.of(15, 0)),
                "https://images.unsplash.com/photo-1460925895917-afdab827c52f?auto=format&fit=crop&w=1200&q=85",
                "Performance",
                120,
                listOf(
                    "Efficiency baseline",
                    "Root-cause analysis",
                    "Savings opportunity register",
                    "30-day improvement plan",
                ),
            ),
            ServiceModel(
                11,
                "Workforce Planning Sprint",
                "Translate business demand into a practical workforce plan covering roles, skills, capacity, and hiring priorities.",
                245.0,
                listOf(LocalTime.of(10, 30), LocalTime.of(16, 30)),
                "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?auto=format&fit=crop&w=1200&q=85",
                "People & Organisation",
                105,
                listOf(
                    "Demand forecast",
                    "Skills gap assessment",
                    "Workforce scenarios",
                    "Hiring priorities",
                ),
            ),
            ServiceModel(
                12,
                "Governance & Decision Design",
                "Reduce decision friction with clear forums, authority levels, escalation paths, and information flows.",
                230.0,
                listOf(LocalTime.of(9, 30), LocalTime.of(14, 30)),
                "https://images.unsplash.com/photo-1531497865144-0464ef8fb9a9?auto=format&fit=crop&w=1200&q=85",
                "Strategic Planning",
                90,
                listOf(
                    "Decision inventory",
                    "Authority matrix",
                    "Governance calendar",
                    "Escalation protocol",
                ),
            ),
        )

    fun observeAll(): Flow<List<ServiceModel>> {
        return flowOf(services)
    }

    fun observeById(id: Int): Flow<ServiceModel?> {
        val service = services.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): ServiceModel? {
        return services.firstOrNull { service -> service.id == id }
    }
}
